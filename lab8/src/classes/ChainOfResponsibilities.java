package classes;

import java.util.ArrayList;
import java.util.List;

public class ChainOfResponsibilities {
    static class Handler {
        void worker(Chain chain) {
            chain.process();
        }
    }

    static class Chain {

        private List<Handler> handlers = new ArrayList<>();
        private int n = 0;

        private void setWorkers(int count) {
            int i = 0;
            while (i++ < count) {
                handlers.add(new Handler());
            }
        }

        public void process() {
            if (n < handlers.size()) {
                Handler handler = handlers.get(n++);
                handler.worker(this);
            }
        }
    }

    public static void work() {
        Chain chain = new Chain();
        chain.setWorkers(50000);
        chain.process();
    }
}
