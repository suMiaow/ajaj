package com.meme.lucene;

import lombok.experimental.UtilityClass;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
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
import java.util.List;
import java.util.function.Function;

@UtilityClass
public class LuceneUtils {

    public static <T> void index(Path indexPath, Analyzer analyzer, Function<T, Document> function, List<T> dataList) throws IOException {
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
}
