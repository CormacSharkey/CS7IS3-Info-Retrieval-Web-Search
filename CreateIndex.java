import org.apache.lucene.analysis.Analyzer;



public class CreateIndex {
    Analyzer analyzer = new StandardAnalyzer();
    IndexWrite writer = new IndexWriter("../index", analyzer, false);
    
}