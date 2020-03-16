package app.swellmap;

import junit.framework.TestCase;

public class TestConfig extends TestCase {

    private final String configPath = "src/test/resources/config.json";

    @Override
    protected void setUp() throws Exception {
        ConfigHandler.setConfigPath(configPath);
    }

    public void testConfigName() {
        try {
            ConfigHandler conf = ConfigHandler.getInstance();
            assertTrue(conf.getName().equals("Swellmap App"));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
    
    public void testConfigDbPath() {
        try {
            ConfigHandler conf = ConfigHandler.getInstance();
            String dbPath = conf.getDbPath();
            assertEquals(dbPath, ConfigHandler.getInstance().getDbPath());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
    
    public void testProxyHost() {
        try {
            ConfigHandler conf = ConfigHandler.getInstance();
            String proxyHost = conf.getProxyHost();
            assertEquals(proxyHost, "auksquid2");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
    
    public void testProxyPort() {
        try {
            ConfigHandler conf = ConfigHandler.getInstance();
            int proxyPort = conf.getProxyPort();
            assertEquals(3128, proxyPort);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
