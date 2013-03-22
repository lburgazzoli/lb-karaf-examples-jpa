package lb.examples.karaf.zookeeper.server.cmd;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 */
public class ZKUtils {
    /**
     *
     * @param properties
     * @throws Exception
     */
    public static void createIdFile(Properties properties) throws Exception {
        String serverId = properties.getProperty("server.id");
        if (serverId != null) {
            properties.remove("server.id");
            File myId = new File(properties.getProperty("dataDir"), "myid");
            if (myId.exists() && !myId.delete()) {
                throw new IOException("Failed to delete " + myId);
            }
            if (myId.getParentFile() == null || (!myId.getParentFile().exists() && !myId.getParentFile().mkdirs()))  {
                throw new IOException("Failed to create " + myId.getParent());
            }
            FileOutputStream fos = new FileOutputStream(myId);
            try {
                fos.write((serverId + "\n").getBytes());
            } finally {
                fos.close();
            }
        }
    }
}
