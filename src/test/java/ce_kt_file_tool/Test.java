package ce_kt_file_tool;

import java.io.IOException;
import java.nio.file.*;

public class Test {

    public static void main(String[] args) throws IOException, InterruptedException {

        WatchService watcher = FileSystems.getDefault().newWatchService();

        Paths.get("C:/temp/ce_kt").register(watcher,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        while (true) {
            WatchKey key = watcher.take();
            System.out.println("Änderung");

            for (WatchEvent<?> event : key.pollEvents())
                System.out.println("Kind: " + event.kind() + ", Path: " + event.context());

            key.reset();
        }
    }
}
