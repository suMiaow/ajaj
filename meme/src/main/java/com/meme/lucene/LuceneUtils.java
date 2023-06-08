package com.meme.lucene;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Slf4j
@UtilityClass
public class LuceneUtils {

    public static <T> void index(Path indexPath, Analyzer analyzer, List<T> dataList, Function<T, Document> function) throws IOException {
        try (Directory indexDirectory = FSDirectory.open(indexPath)) {
            IndexWriter writer = new IndexWriter(indexDirectory, new IndexWriterConfig(analyzer));

            for (T data : dataList) {
                writer.addDocument(function.apply(data));
            }
            writer.close();
        }
    }

    public static List<Document> search(Path indexPath, Analyzer analyzer, String f, String q, int n) throws IOException, ParseException {
        List<Document> docList = new ArrayList<>();

        try (DirectoryReader reader = DirectoryReader.open(FSDirectory.open(indexPath))) {
            IndexSearcher searcher = new IndexSearcher(reader);

            QueryParser parser = new QueryParser(f, analyzer);
            Query query = parser.parse(q);
            ScoreDoc[] scoreDocs = searcher.search(query, n).scoreDocs;

            for (ScoreDoc scoreDoc : scoreDocs) {
                docList.add(searcher.doc(scoreDoc.doc));
            }
        }
        return docList;
    }

    public static void main(String[] args) throws IOException, ParseException {
        Path indexPath = Path.of("D://temp/lucene_index");
        Analyzer analyzer = new StandardAnalyzer();

        List<List<String>> dataList = Arrays.asList(
                Arrays.asList(
                        "aaa jsjoo jvkdk ",
                        "saslei jbo jvsx ",
                        " kasl3e iifjieao 1129",
                        " aslh 19300 jfo"
                ),
                Arrays.asList(
                        "fjo238  93 j30t hg0322",
                        " fhohg 320984 j8f30vj ",
                        " fj320j uj0 0j ",
                        " j jgoj"
                ),
                Arrays.asList(
                        ""
                ),
                Arrays.asList(
                        "fdjo 2314341",
                        "123j jfj lf"
                )
        );

        LuceneUtils.index(indexPath, analyzer, dataList, list -> {
                Document document = new Document();

                for (String data : list) {
                    document.add(new Field("data", data, TextField.TYPE_STORED));
                }
                return document;
        });

        List<Document> docList = LuceneUtils.search(indexPath, analyzer, "data", "aaa", 2);

        log.info("docList: {}", docList);
    }
}
