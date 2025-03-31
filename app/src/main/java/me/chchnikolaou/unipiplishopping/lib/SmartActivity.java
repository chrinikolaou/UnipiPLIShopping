package me.chchnikolaou.unipiplishopping.lib;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import me.chchnikolaou.unipiplishopping.MainActivity;
import me.chchnikolaou.unipiplishopping.NearMeActivity;
import me.chchnikolaou.unipiplishopping.R;
import me.chchnikolaou.unipiplishopping.lib.builder.ProductBuilder;
import me.chchnikolaou.unipiplishopping.lib.builder.ToastBuilder;
import me.chchnikolaou.unipiplishopping.lib.helper.DatabaseHelper;
import me.chchnikolaou.unipiplishopping.lib.helper.LocationHelper;

public abstract class SmartActivity extends AppCompatActivity implements ActivityLib {

    private final int layoutId;
    private final int windowId;

    private boolean topMenu;
    private boolean bottomMenu;

    /*
     * Firebase Variables
     */
    private FirebaseAuth auth;
    private FirebaseUser user;
    private FirebaseDatabase database;
    private DatabaseReference reference;


    public SmartActivity(int layoutId, int windowId) {
        this.layoutId = layoutId;
        this.windowId = windowId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(layoutId);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(windowId), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    public int getLayout() { return layoutId; }
    public int getWindowId() { return windowId; }

    public void initiate() {

        if(bottomMenu) inflateMenu();
        if(topMenu) inflateTopMenu();

        database = FirebaseDatabase.getInstance("https://unipiplishopping-b1713-default-rtdb.europe-west1.firebasedatabase.app/");

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        reference = database.getReference("products");
        loadData();

    }

    @Override
    public void loadData() {
        DatabaseHelper.add(getReference(), new ProductBuilder()
                .setTitle("Asus Zenbook S14")
                .setDescription(List.of("Ζήσε την επόμενη γενιά φορητών υπολογιστών με το ASUS Zenbook S 14 OLED UX5406SA, το απόλυτο laptop για παραγωγικότητα, δημιουργικότητα και κορυφαίες εμπειρίες AI. Με Intel Core Ultra 7 258V, 32GB RAM και 1TB SSD M.2, προσφέρει ασύγκριτη ταχύτητα και απόδοση. Το NPU 48 TOPS ενισχύει τις λειτουργίες AI, ενώ το Ambient Cooling διατηρεί χαμηλά επίπεδα θορύβου. Η εκπληκτική 3K ASUS Lumina OLED οθόνη αφής και το σύστημα τεσσάρων ηχείων εξασφαλίζουν καθηλωτική εμπειρία θέασης και ήχου, σε ένα μεταλλικό σώμα πάχους μόλις 1,1 εκ."))
                .setReleaseDate("2024-09-12")
                .setCost(1499.00)
                .setLocations(LocationHelper.generate(ThreadLocalRandom.current().nextInt(200,1000)))
                .setImageId(R.drawable.zenbook)
                .build());

        DatabaseHelper.add(getReference(), new ProductBuilder()
                .setTitle("Asus Tuf Gaming A 16")
                .setDescription(List.of("Αν αναζητάς έναν φορητό υπολογιστή για gaming και επαγγελματική χρήση, το Asus TUF Gaming A15 FA507UV-LP005W είναι μια εξαιρετική επιλογή. Η οθόνη του διαθέτει 15.6'' FHD IPS με ρυθμό ανανέωσης 144Hz για ομαλή απόδοση εικόνας. Εξοπλίζεται με τον επεξεργαστή Ryzen 9-8945H για γρήγορη απόκριση και αξιόπιστη επίδοση σε εφαρμογές και παιχνίδια. Ο αποθηκευτικός του χώρος είναι 512GB SSD, προσφέροντας γρήγορη αποθήκευση και αντοχή. Η κάρτα γραφικών GeForce RTX 4060 είναι ιδανική για gaming και προσφέρει εξαιρετική γραφική απόδοση για τα πιο απαιτητικά παιχνίδια. Τα 16GB RAM εξασφαλίζουν ομαλή λειτουργία και απόδοση πολλαπλών εφαρμογών ταυτόχρονα, ενώ το λειτουργικό σύστημα Windows 11 Home προσφέρει φιλική προς τον χρήστη εμπειρία."))
                .setReleaseDate("2024-01-09")
                .setCost(1599.99)
                .setLocations(LocationHelper.generate(ThreadLocalRandom.current().nextInt(200,1000)))
                .setImageId(R.drawable.zenbook)
                .build());

        DatabaseHelper.add(getReference(), new ProductBuilder()
                .setTitle("HP 15 FC0008NVR5")
                .setDescription(List.of("Άλλαξε τη δυναμική του δωματίου σου με το σχολαστικά κατασκευασμένο και εξαιρετικά ελαφρύ HP 15-FC0008NV. Προώθησε την παραγωγικότητα σου με τον ισχυρό AMD Ryzen 5 7520U επεξεργαστή, εσνωματωμένη κάρτα γραφικών AMD Radeon και με σύνδεση Wi-Fi 6. Διαθέτει οθόνη 15.6\" με τεχνολογία panel IPS, ανάλυση 1920 x 1080 pixels και σου προσφέρει απίστευτη απόδοση των λεπτομερειών και ρεαλιστικά χρώματα. Το HP 15-FC0008NV παρέχει κινητικότητα πέρα από το προσδοκίες σε ένα εξαιρετικά κατασκευασμένο Laptop."))
                .setReleaseDate("2023-05-02")
                .setCost(599.00)
                .setLocations(LocationHelper.generate(ThreadLocalRandom.current().nextInt(200,1000)))
                .setImageId(R.drawable.hp15).build());

        DatabaseHelper.add(getReference(), new ProductBuilder()
                .setTitle("Huawei Matebook d14")
                .setDescription(List.of("Μείνετε συνδεδεμένοι με τους δικούς σας όρους. Η κινητήρια δύναμη της παραγωγικότητάς σας, παντού και πάντα. Αειφόρος και προοδευτική σχεδίαση. Το laptop 15,6\" HP διαθέτει όλες τις δυνατότητες που απαιτούνται για να διατηρείτε την παραγωγικότητά σας σε υψηλά επίπεδα με χαμηλό κόστος και υψηλή περιβαλλοντική υπευθυνότητα.\n" +
                        "Κατασκευασμένο από ανακυκλωμένα υλικά, αυτό το ισχυρό laptop HP διαθέτει επεξεργαστή AMD, άφθονο χώρο αποθήκευσης και καθηλωτικά γραφικά. Η αναλογία οθόνης προς σώμα που φτάνει στο 85% και το στενό πλαίσιο σε 3 πλευρές προσφέρουν άφθονο χώρο για να κάνετε και να βλέπετε περισσότερα.\n" +
                        "Το HP 15-fc0008nv απευθύνεται σε ευρύ φάσμα χρηστών, κυρίως σε εκείνους που αναζητούν έναν αξιόπιστο φορητό υπολογιστή για την καθημερινή τους χρήση. Αυτό το μοντέλο προσφέρει ικανοποιητικές επιδόσεις για τις καθημερινές εργασίες όπως αναπαραγωγή πολυμέσων, σερφάρισμα στο διαδίκτυο, επεξεργασία εγγράφων και άλλες βασικές εργασίες γραφείου.\n" +
                        "Είναι μία καλή επιλογή για φοιτητές, επαγγελματίες, αλλά και για οικιακή χρήση. Ο σχεδιασμός του είναι απλός και λειτουργικός, ενώ η οθόνη των 15 ιντσών προσφέρει ικανοποιητική εμπειρία χρήσης.\n" +
                        "Συνολικά, το HP 15-fc0007nv απευθύνεται σε χρήστες που χρειάζονται έναν φορητό υπολογιστή για καθημερινή χρήση και βασικές εργασίες\n"))
                .setReleaseDate("2024-08-04")
                .setCost(720.00)
                .setLocations(LocationHelper.generate(ThreadLocalRandom.current().nextInt(200,1000)))
                .setImageId(R.drawable.huawei).build());

        DatabaseHelper.add(getReference(), new ProductBuilder()
                .setTitle("Lenovo v15 g4")
                .setDescription(List.of("Το Lenovo V15 G4 AMN 15.6\" FHD με επεξεργαστή Ryzen 3-7320U και 16GB μνήμης απευθύνεται σε χρήστες που χρειάζονται έναν φορητό υπολογιστή για καθημερινές εργασίες, όπως αναπαραγωγή πολυμέσων, περιήγηση στο διαδίκτυο, γραφειοκρατικές εργασίες και επεξεργασία κειμένου.\n" +
                        "Ο επεξεργαστής Ryzen 3-7320U προσφέρει αρκετή ισχύ για τις παραπάνω εργασίες, ενώ η μνήμη 16GB RAM DDR5 προτείνεται για απαιτητικές εφαρμογές όπως επεξεργασία εικόνας και video, παραγωγή μουσικής και επεξεργασία ήχου.\n" +
                        ". Επιπλέον, η οθόνη 15.6\" FHD (Full HD) παρέχει καλή ποιότητα εικόνας για την αναπαραγωγή πολυμέσων και την προβολή περιεχομένου.\n" +
                        "Συνολικά, αυτός ο φορητός υπολογιστής είναι κατάλληλος για τους απλούς χρήστες που αναζητούν έναν αξιόπιστο και προσιτό υπολογιστή για τις καθημερινές τους ανάγκες.\n"))
                .setReleaseDate("2023-09-09")
                .setCost(469.00)
                .setLocations(LocationHelper.generate(ThreadLocalRandom.current().nextInt(200,1000)))
                .setImageId(R.drawable.lenovo).build());

        DatabaseHelper.add(getReference(), new ProductBuilder()
                .setTitle("Huawei iPura 70")
                .setDescription(List.of("Απόλαυσε εμβληματική σχεδίαση και κορυφαία οπτική εμπειρία με το Huawei Pura 70 Pro. Η εντυπωσιακή 6.8'' LTPO OLED οθόνη και η ανθεκτική κατασκευή σού χαρίζουν τη σιγουριά που χρειάζεσαι, ενώ το σύστημα τριπλής κάμερας 50MP απαθανατίζει εικόνες ασύλληπτης ομορφιάς.\n" +
                        "\n" +
                        "Χάρη στον οκταπύρηνο επεξεργαστή, τη μεγάλη μπαταρία 5050mAh και την ταχεία ενσύρματη και ασύρματη φόρτιση HUAWEI SuperCharge, εξασφάλισε υψηλές επιδόσεις και απρόσκοπτη χρήση. Με το Huawei Pura 70 Pro, επέκτεινε τους ορίζοντές σου μέσα από πρωτοποριακές προοπτικές!\n"))
                .setReleaseDate("2024-07-25")
                .setCost(899.00)
                .setLocations(LocationHelper.generate(ThreadLocalRandom.current().nextInt(200,1000)))
                .setImageId(R.drawable.huaweipura).build());

        DatabaseHelper.add(getReference(), new ProductBuilder()
                .setTitle("iPhone 15")
                .setDescription(List.of("Το iPhone 15 διαθέτει Dynamic Island, κύρια κάμερα 48MP και υποδοχή USB-C. Όλα αυτά σε ένα ανθεκτικό σχεδιασμό από γυαλί εμποτισμένο με χρώμα και αλουμίνιο."))
                .setReleaseDate("2023-07-25")
                .setCost(899.00)
                .setLocations(LocationHelper.generate(ThreadLocalRandom.current().nextInt(200,1000)))
                .setImageId(R.drawable.iphone15).build());

        DatabaseHelper.add(getReference(), new ProductBuilder()
                .setTitle("Samsung a55")
                .setDescription(List.of("Μεγιστοποίησε την εμπειρία σου με το Samsung Galaxy A55 5G, ένα awesome smartphone που θα σε κατακτήσει! Η οθόνη Super AMOLED 6.6'' χαρίζει ολοζώντανες εικόνες και υπόσχεται ευρεία προβολή, ενώ ο ρυθμός ανανέωσης 120Hz επιτρέπει ομαλή κύλιση. Δημιούργησε επαγγελματικές φωτογραφίες με την κύρια κάμερα 50 MP, την υπερευρυγώνια 12 MP και την κάμερα macro 5 MP και βγάλε selfie υψηλής ανάλυσης με την μπροστινή κάμερα 32 MP.\n" +
                        "\n" +
                        "Ολοκλήρωσε τις καθημερινές σου εργασίες με τον υψηλής απόδοσης οκταπύρηνο επεξεργαστή, τα 8GB RAM και τα 128GB αποθηκευτικού χώρου και ζήσε την ημέρα σου όπως εσύ θέλεις με την μπαταρία χωρητικότητας 5000 mAh!\n"))
                .setReleaseDate("2024-04-29")
                .setCost(499.00)
                .setLocations(LocationHelper.generate(ThreadLocalRandom.current().nextInt(200,1000)))
                .setImageId(R.drawable.a55).build());


        DatabaseHelper.add(getReference(), new ProductBuilder()
                .setTitle("Samsung S24")
                .setDescription(List.of("Με το Samsung Galaxy S24 Ultra και τη δύναμη του AI σε περιμένει μια εκπληκτική εμπειρία! Βρες όσα χρειάζεσαι γρήγορα και εύκολα με μια απλή κίνηση, εξοικονόμησε χρόνο διαβάζοντας μόνο όσα σε ενδιαφέρουν και απαθανάτισε την κάθε στιγμή σου όπως την ονειρεύτηκες."))
                .setReleaseDate("2024-03-25")
                .setCost(1199.00)
                .setLocations(LocationHelper.generate(ThreadLocalRandom.current().nextInt(200,1000)))
                .setImageId(R.drawable.s24).build());

        DatabaseHelper.add(getReference(), new ProductBuilder()
                .setTitle("Xiaomi IPoco X6")
                .setDescription(List.of("Με την οθόνη 6.67\" Flow AMOLED 1.5K CrystalRes Display τα χρώματα είναι ζωντανά και η απεικόνιση ευκρινής ακόμα και κάτω από το φως του ήλιου. Υποστηρίζει 68 δισεκατομμύρια χρώματα, ενώ ο ρυθμός ανανέωσης 120Hz εξασφαλίζει ομαλή κύλιση και απολαυστικό gaming. Το smartphone 5G με συνδεσιμότητα που θα καλύπτει μελλοντικές απαιτήσεις, έχει μικρότερο λανθάνοντα χρόνο με μεγαλύτερη ταχύτητα λήψης.\n" + "Έχει βελτιστοποιηθεί ειδικά με γνώμονα ένα ευρύτερο φάσμα σεναρίων για μια σύνδεση στην οποία μπορείτε να βασίζεστε. Η μεγάλη μπαταρία 5100mAh εξασφαλίζει μέχρι και 20+ ώρες online video streaming και 11+ ώρες πλοήγησης στο διαδίκτυο, καθώς και μέχρι 31+ ώρες ομιλίας, σύμφωνα με μετρήσεις της εταιρίας. Διαθέτει ταχεία φόρτιση 67W για 100% σε μόλις 44 λεπτά.\n"))
                .setReleaseDate("2024-08-15")
                .setCost(298.99)
                .setLocations(LocationHelper.generate(ThreadLocalRandom.current().nextInt(200,1000)))
                .setImageId(R.drawable.x6).build());

        DatabaseHelper.add(getReference(), new ProductBuilder()
                .setTitle("iPad 2022")
                .setDescription(List.of("Premium σχεδιασμός και κορυφαία χαρακτηριστικά για να δημιουργείς περισσότερο χωρίς περιορισμούς!\n" +
                        "\n" +
                        "Πολύχρωμο, επανασχεδιασμένο και πιο ευέλικτο από ποτέ. Με οθόνη, απ’ άκρη σ’ άκρη Liquid Retina 10,9 ιντσών και τέσσερα υπέροχα χρώματα. Το iPad προσφέρει έναν μοναδικό τρόπο για να δημιουργείς, να παραμένεις συνδεδεμένος και να είσαι αποτελεσματικός σε ό,τι κάνεις.\n"))
                .setReleaseDate("2022-11-11")
                .setCost(419.00)
                .setLocations(LocationHelper.generate(ThreadLocalRandom.current().nextInt(200,1000)))
                .setImageId(R.drawable.ipad).build());

        DatabaseHelper.add(getReference(), new ProductBuilder()
                .setTitle("Huawei MatePadSE")
                .setDescription(List.of("Ξεκλείδωσε τη δημιουργικότητά σου και οπτικοποίησε τις ιδέες σου χάρη στο Huawei MatePad SE 11''. Ιδανικό για δημιουργία και σχεδιασμένο για ευκολία στη χρήση, ενσωματώνει μεγάλη οθόνη 11'' Full HD+ και πιστοποίηση TÜV Rheinland για άνετη προβολή ανά πάσα στιγμή.\n" +
                        "\n" +
                        "Κομψό και απόλυτα λειτουργικό, το Huawei MatePad SE 11'' διαθέτει λεπτό, ενιαίο σχεδιασμό από κράμα αλουμινίου. Το τετραπλό συμμετρικό σύστημα ηχείων και το HUAWEI Histen 9.0 εξασφαλίζουν μια καθηλωτική ακουστική εμπειρία, ενώ η μπαταρία υψηλής χωρητικότητας 7700 mAh και η ταχεία φόρτιση 22.5W είναι εδώ για να υποστηρίξουν πλήρως τις καθημερινές σου ανάγκες.\n"))
                .setReleaseDate("2023-12-17")
                .setCost(178.99)
                .setLocations(LocationHelper.generate(ThreadLocalRandom.current().nextInt(200,1000)))
                .setImageId(R.drawable.matepad).build());

        DatabaseHelper.add(getReference(), new ProductBuilder()
                .setTitle("Lenovo TAB M11")
                .setDescription(List.of("Το Lenovo Tab M11 έρχεται να δώσει στυλ, ταχύτητα και άνετη χρήση και στην ψυχαγωγία, αλλά και στη μάθηση σου. Ξεκλείδωσε έναν υπέροχο κινηματογραφικό κόσμο ανακαλύπτοντας εξαιρετικά καθαρή ροή χάρη στη μεγάλη οθόνη 11'' και δώσε την ευκαιρία στα νεαρά μυαλά να εξερευνήσουν καθηλωτικά ηχητικά τοπία χάρη στα βελτιστοποιημένα από το Dolby Atmos τετραπλά ηχεία.\n" +
                        "\n" +
                        "Σχεδιασμένη για γρήγορους ρυθμούς, αυτή η συσκευή ξεπερνά τις προσδοκίες σου, χαρίζοντάς σου ομαλό gaming και απρόσκοπτο multitasking.\n"))
                .setReleaseDate("2023-10-24")
                .setCost(179.90)
                .setLocations(LocationHelper.generate(ThreadLocalRandom.current().nextInt(200,1000)))
                .setImageId(R.drawable.tab).build());

        DatabaseHelper.add(getReference(), new ProductBuilder()
                .setTitle("Samsung Galaxy TabA9")
                .setDescription(List.of("Κομψά και λειτουργικά, τα Galaxy Tab A9 | Tab A9+ διαθέτουν κομψή σχεδίαση και λείο μεταλλικό σώμα. Διαθέσιμα σε δύο μεγέθη οθόνης, 8.7'' και 11.0'', τα νέα Galaxy Tab A9 Series είναι σχεδιασμένα για να είναι εκπληκτικά φορητά και να υποστηρίζουν τις καθημερινές σου ανάγκες.\n" +
                        "\n" +
                        "Η μεγάλη οθόνη ομαλού ρυθμού ανανέωσης και τα ηχεία με τον καθηλωτικό ήχο Dolby Atmos όχι μόνο σου επιτρέπουν να βυθιστείς στα αγαπημένα σου βίντεο και παιχνίδια, αλλά παρέχουν επίσης διασκεδαστικές μεθόδους εκμάθησης για τα παιδιά, ενώ ο ισχυρός επεξεργαστής και η ευρεία συνδεσιμότητα σε βοηθούν να παραμένεις συνδεδεμένος και να ολοκληρώνεις τα καθημερινά σου καθήκοντα σαν επαγγελματίας.\n"))
                .setReleaseDate("2024-07-25")
                .setCost(199.90)
                .setLocations(LocationHelper.generate(ThreadLocalRandom.current().nextInt(200,1000)))
                .setImageId(R.drawable.taba9).build());

        DatabaseHelper.add(getReference(), new ProductBuilder()
                .setTitle("Xiaomi Redmi Pad Pro")
                .setDescription(List.of("Απόλαυσε την άνεση και την ποιότητα του Xiaomi Redmi Pad Pro 5G με οθόνη LCD 12.1'' Quad HD+ και αναλογία διαστάσεων 16:10. Με ρυθμό ανανέωσης 120Hz και μέγιστη φωτεινότητα 600 nits, προσφέρει άριστη απεικόνιση, ενώ οι τριπλές πιστοποιήσεις TÜV Rheinland και το DC dimming μειώνουν την καταπόνηση των ματιών.\n" +
                        "\n" +
                        "Εξοπλισμένο με μπαταρία 10.000mAh για αυτονομία έως 33.9 ημέρες και ταχεία φόρτιση 33W, το Redmi Pad Pro 5G διασφαλίζει διάρκεια και απόδοση. Ενσωματώνει διπλή SIM και διπλή υποστήριξη 5G για να πραγματοποιείς κλήσεις ανά πάσα στιγμή, καθώς και υποστήριξη Wi-Fi 6E. Με τον επεξεργαστή Snapdragon 7s Gen 2, τέσσερα ηχεία Dolby Atmos και προεγκατεστημένο το Xiaomi HyperOS, προσφέρει κορυφαία οπτικοακουστική εμπειρία και εύκολη διασύνδεση με άλλες συσκευές Xiaomi.\n"))
                .setReleaseDate("2023-06-28")
                .setCost(329.90)
                .setLocations(LocationHelper.generate(ThreadLocalRandom.current().nextInt(200,1000)))
                .setImageId(R.drawable.redmipad).build());

    }

    @Override
    public void unsupported(View view) {
       new ToastBuilder(this)
               .setMessage("This feature is not supported yet.")
               .setStyle(me.chchnikolaou.unipiplishopping.lib.Toast.ToastStyle.INFO)
               .setDuration(Toast.LENGTH_SHORT).build()
               .show();
    }

    @Override
    public void showActivity(View view, Class<?> clazz) {

        Intent intent = new Intent(this, clazz);
        super.startActivity(intent);
    }

    @Override
    public void showActivity(View view, Class<?> clazz, Map<String, Object> data) {

        Intent intent = new Intent(this, clazz);
        for(Map.Entry<String, Object> en : data.entrySet()) {
            if(en.getValue() instanceof Integer)  {
                int x = (int) en.getValue();
                intent.putExtra(en.getKey(), x);
            }

            if(en.getValue() instanceof String) {
                intent.putExtra(en.getKey(), en.getValue().toString());
            }

        }
        super.startActivity(intent);
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if(view==null) return;

        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        view.clearFocus();
    }

    /*
     * Main Menu of UnipiPLIShopping Software
     */
    private SmartActivity inflateMenu() {

        LinearLayout layout = findViewById(R.id.menu);

        LayoutInflater inflater = LayoutInflater.from(this);

        String[] titles = new String[] {"Home", "Search", "Near Me"};
        int[] icons = new int[] {R.drawable.home, R.drawable.search, R.drawable.near_me};


        for(int i = 0; i < titles.length; i++) {

            View item = inflater.inflate(R.layout.layout_menu_icon, layout, false);
            item.findViewById(R.id.menu_image).setBackgroundResource(icons[i]);
            TextView textView = item.findViewById(R.id.menu_text);
            textView.setText(titles[i]);

            item.setOnClickListener((listener)-> {
//                Toast.makeText(this, "Clicked: " + textView.getText(), Toast.LENGTH_SHORT).show();

                switch(textView.getText().toString().toLowerCase()) {
                    case "home":
                        if(!(this instanceof MainActivity)) showActivity(null, MainActivity.class);
                        break;

                    case "search":

                        break;

                    case "near me":
                        if(!(this instanceof NearMeActivity)) showActivity(null, NearMeActivity.class);
                        break;
                }

            });

            layout.addView(item);

        }


        return this;
    }


    public abstract void inflateTopMenu();
    public abstract void show(View view);


    public SmartActivity setTopMenu(boolean value) {
        topMenu=value;
        return this;
    }

    public SmartActivity setBottomMenu(boolean value) {
        bottomMenu=value;
        return this;
    }

    public FirebaseAuth getAuth() { return auth; }
    public FirebaseUser getUser() { return user; }
    public FirebaseDatabase getDatabase() { return database; }
    public DatabaseReference getReference() { return reference; }

    public void setAuth(FirebaseAuth auth) {
        this.auth = auth;
    }

    public void setUser(FirebaseUser user) {
        this.user = user;
    }

    public void setDatabase(FirebaseDatabase database) {
        this.database = database;
    }

    public void setReference(DatabaseReference reference) {
        this.reference = reference;
    }

    public String getFullName() {
        SharedPreferences preferences = getSharedPreferences("personal", MODE_PRIVATE);
        return preferences.getString("name", "user-"+auth.getUid());
    }

    public int getAge() {
        SharedPreferences preferences = getSharedPreferences("personal", MODE_PRIVATE);
        return preferences.getInt("age", 0);
    }

    public String getGender() {
        SharedPreferences preferences = getSharedPreferences("personal", MODE_PRIVATE);
        return preferences.getString("gender", "Unknown");
    }

    public void setFullName(String name) {
        SharedPreferences preferences = getSharedPreferences("personal", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name", name);
        editor.apply();
    }

    public void setAge(int age) {
        SharedPreferences preferences = getSharedPreferences("personal", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("age", age);
        editor.apply();
    }

    public void setGender(String gender) {
        SharedPreferences preferences = getSharedPreferences("personal", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("gender", gender);
        editor.apply();
    }


}
