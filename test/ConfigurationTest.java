import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConfigurationTest {

    @Test
    public void testAjoutReussi() {
        Composant cpu = new Composant("CPU", "Intel", "Core i5-13600K", 330);
        Composant carteMere = new Composant("Carte mère", "Asus", "ROG Strix B760", 200);
        Configuration config = new Configuration("Build Test", 1000, new Composant[]{});

        assertTrue(config.ajouter(cpu));
        assertTrue(config.ajouter(carteMere));
    }

    @Test
    public void testAjoutMemeCategorie() {
        Composant cpu1 = new Composant("CPU", "Intel", "Core i5-13600K", 330);
        Composant cpu2 = new Composant("CPU", "AMD", "Ryzen 5 5700X", 260);
        Configuration config = new Configuration("Build Test", 1000, new Composant[]{cpu1});

        assertFalse(config.ajouter(cpu2));
    }

    @Test
    public void testAjoutDepassementPrixMaximal() {
        Composant cpu = new Composant("CPU", "Intel", "Core i5-13600K", 330);
        Composant gpu = new Composant("GPU", "Nvidia", "RTX 3080", 800);
        Configuration config = new Configuration("Build Test", 1000, new Composant[]{cpu});

        assertFalse(config.ajouter(gpu));
    }

    @Test
    public void testAjoutDepassementNombreMaxComposants() {
        Composant[] composants = new Composant[20];
        for (int i = 0; i < 20; i++) {
            composants[i] = new Composant("Composant" + i, "Marque" + i, "Nom" + i, i * 10);
        }
        Configuration config = new Configuration("Build Test", 1000, composants);

        Composant nouveauComposant = new Composant("Nouveau", "MarqueNouveau", "Nouveau", 100);
        assertFalse(config.ajouter(nouveauComposant));
    }
}