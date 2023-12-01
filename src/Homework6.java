import java.util.HashMap;
import java.util.Map;

class Homework6 {

    public static void main(String[] args) {
        Document document = new Document(1, "Doc123");

        ReportService reportService = new ReportService();
        reportService.registerStrategy("pdf", new PdfReportStrategy());
        reportService.registerStrategy("json", new JsonReportStrategy());
        reportService.registerStrategy("xml", new XmlReportStrategy());

        byte[] pdfReport = reportService.createReport(document, "pdf");
        byte[] jsonReport = reportService.createReport(document, "json");
        byte[] xmlReport = reportService.createReport(document, "xml");
    }

    static class ReportService {
        private final Map<String, ReportStrategy> strategies = new HashMap<>();

        public void registerStrategy(String type, ReportStrategy strategy) {
            strategies.put(type, strategy);
        }

        public byte[] createReport(Document document, String reportType) {
            ReportStrategy strategy = strategies.get(reportType);
            if (strategy == null) {
                throw new UnsupportedOperationException("No strategy registered for type: " + reportType);
            }
            return strategy.createReport(document);
        }
    }

    interface ReportStrategy {
        byte[] createReport(Document document);
    }

    static class PdfReportStrategy implements ReportStrategy {
        @Override
        public byte[] createReport(Document document) {
            System.out.println("Creating PDF report for document: " + document.getNumber());
            return new byte[0];
        }
    }

    static class JsonReportStrategy implements ReportStrategy {
        @Override
        public byte[] createReport(Document document) {
            System.out.println("Creating JSON report for document: " + document.getNumber());
            return new byte[0];
        }
    }

    static class XmlReportStrategy implements ReportStrategy {
        @Override
        public byte[] createReport(Document document) {
            System.out.println("Creating XML report for document: " + document.getNumber());
            return new byte[0];
        }
    }

    static class Document {
        private final long id;
        private final String number;

        public Document(long id, String number) {
            this.id = id;
            this.number = number;
        }

        public long getId() {
            return id;
        }

        public String getNumber() {
            return number;
        }
    }
}


