package com.meme.lucene;

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
import org.apache.lucene.util.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class TempTest {

    @Test
    void test() {
        try {
            Analyzer analyzer = new StandardAnalyzer();

            Path indexPath = Files.createTempDirectory("tempIndex");
            Directory directory = FSDirectory.open(indexPath);
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            IndexWriter iwriter = new IndexWriter(directory, config);
            Document doc = new Document();
            String text = "This is the text to be indexed.";
            doc.add(new Field("fieldname", text, TextField.TYPE_STORED));
            iwriter.addDocument(doc);
            iwriter.close();

            // Now search the index:
            DirectoryReader ireader = DirectoryReader.open(directory);
            IndexSearcher isearcher = new IndexSearcher(ireader);

            // Parse a simple query that searches for "text":
            QueryParser parser = new QueryParser("fieldname", analyzer);
            Query query = parser.parse("text");
            ScoreDoc[] hits = isearcher.search(query, 10).scoreDocs;
            Assertions.assertEquals(1, hits.length);

            // Iterate through the results:
            for (ScoreDoc hit : hits) {
                Document hitDoc = isearcher.doc(hit.doc);
                Assertions.assertEquals("This is the text to be indexed.", hitDoc.get("fieldname"));
            }
            ireader.close();
            directory.close();
            IOUtils.rm(indexPath);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }
    }
}
