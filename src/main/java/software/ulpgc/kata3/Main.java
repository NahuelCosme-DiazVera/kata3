package software.ulpgc.kata3;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {
        List<Person> people = TsvFilePersonLoader. with("hw_25000.tsv").load();
        Map<String, Integer> histogram = new WeightHistogramCalculator(people).calculate();
        MainFrame mainFrame = new MainFrame();
        mainFrame.getHistogramDisplay().show(createHistogram(histogram));
        mainFrame.setVisible(true);
    }

    private static Histogram createHistogram(Map<String, Integer> histogram) {
        return new Histogram() {
            @Override
            public int bins() {
                return histogram.size();
            }

            @Override
            public double[] values() {
                return histogram.values()
                        .stream()
                        .mapToDouble(value -> value)
                        .toArray();
            }
        };
    }
}
