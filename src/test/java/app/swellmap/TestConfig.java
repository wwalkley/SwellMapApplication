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
}
