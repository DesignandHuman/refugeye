package com.refugeye;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Home extends AppCompatActivity {

    public DrawingView drawingView;
    public ListView listView;
    private EditText search;
    private SwipeView swipeView;
    private View successOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final PictoListAdapter pictoListAdapter = new PictoListAdapter(this);

        drawingView = (DrawingView) findViewById(R.id.home_drawing_view);
        drawingView.setupDrawing();
        drawingView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {

                if (event.getAction() == DragEvent.ACTION_DROP) {
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), pictoListAdapter.getItem(pictoListAdapter.selectedPosition).resId);
                    bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() * 2, bitmap.getHeight() * 2, false);
                    drawingView.addBitmap(bitmap, event.getX(), event.getY());

                }
                if (event.getAction() == DragEvent.ACTION_DRAG_STARTED) {
                    swipeView.close();
                }
                return true;
            }
        });

        swipeView = (SwipeView) findViewById(R.id.sliding_pannel);

        listView = (ListView) findViewById(R.id.home_picto_list);


        pictoListAdapter.add(new Picto(R.drawable.all_icons_01, new String[]{"ONG", "NGO", "NVO - nevladna organizacija", "منظمة غير حكومية", "vabaühendus", "ONG", "ONG", "НВО", "ONG", "NGO", "非政府组织机构", "ΜΚΟ"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_02, new String[]{"argent", "money", "denar", "مال", "raha", "dinero", "dinheiro", "новац", "i soldi", "Geld", "钱", "χρἡματα"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_03, new String[]{"don", "gift", "donacija", "هبة مالية", "annetus", "donación", "doação", "поклон", "donazione", "Spende", "捐款", "δωρεἁ"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_04, new String[]{"couverts", "cutlery", "pribor", "أدوات المائدة", "lauanõud", "cubiertos", "talheres", "прибор за јело", "le posate", "Besteck", "餐具", "μαχαιροπἡρουνα"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_05, new String[]{"nourriture", "food", "hrana", "طعام", "toit", "comida", "comida", "храна", "il cibo", "Essen", "食品", "τροφἡ"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_06, new String[]{"europe", "europe", "Europa", "أوروبا", "euroopa", "Europa", "europa", "Европа", "europa", "Europa", "欧洲", "Ευρὡπη"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_07, new String[]{"chrétien", "Christian", "katolik", "مسيحي", "kristlane", "cristiano", "cristão", "хришћанин", "cristiano", "Christ", "基督徒", "Χριστιανὁς"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_08, new String[]{"bible", "bible", "biblija", "الكتاب المقدس", "piibel", "Biblia", "bíblia", "Библија", "bibbia", "Bibel", "圣经", "Βἱβλος"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_09, new String[]{"menorah", "menorah", "menora", "شمعدان", "menora", "candelero", "candeeiro", "јеврејски свећњак", "candelabro", "Menora", "烛台", "κηροπἡγιο"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_10, new String[]{"islam", "Islam", "islam", "إسلام", "islam", "islam", "Islão", "ислам", "islam", "Islam", "伊斯兰教", "Ισλἀμ"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_11, new String[]{"juif", "Jewish", "žid", "يهودي", "juut", "judío", "judeu", "Јеврејски", "ebreo", "jüdisch", "犹太人", "Εβραἱος"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_12, new String[]{"traduction", "translation", "prevod", "ترجمة", "tõlge", "traducción", "tradução", "превод", "traduzione", "Übersetzung", "翻译", "μετἁφραση"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_13, new String[]{"passeport", "passport", "potni list", "جواز سفر", "pass", "pasaporte", "passaporte", "пасош", "passaporto", "Pass", "护照", "διαβατἡριο"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_14, new String[]{"canada", "canada", "Kanada", "كندا", "kanada", "Canadá", "canada", "Канада", "canada", "Kanada", "加拿大", "Καναδἁς"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_15, new String[]{"lesbienne", "lesbian", "lezbijka", "مثليه", "lesbi", "lesbiana", "lésbica", "лезбијка", "lesbica", "lesbisch", "女同性恋", "λεσβἱα"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_16, new String[]{"bébé", "baby", "dojenček", "طفل", "imik", "bebé", "bebé", "беба", "bambino", "Baby", "婴儿", "μωρὁ"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_17, new String[]{"poussette", "stroller", "voziček", "عربة أطفال", "käru", "cochecito", "carrinho", "дечија колица", "passeggino", "Kinderwagen", "儿童推车", "καροτσἁκι"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_18, new String[]{"protéger", "protect", "zavarovati", "حماية", "kaitsma", "proteger", "proteger", "заштитити", "proteggere", "schützen", "保护", "προστατεὑω"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_19, new String[]{"grossesse", "pregnancy", "nosečnost", "حمل", "rasedus", "embarazo", "gravidez", "трудноћа", "gravidanza", "Schwangerschaft", "怀孕", "εγγυμοσὑνη"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_20, new String[]{"parent", "relative", "starši", "أهل", "vanemad", "padres", "pais", "рођак", "genitori", "Eltern", "父母", "γονεἱς"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_21, new String[]{"mariage", "marriage", "poroka", "زواج", "abielu", "matrimonio", "casamento", "брак", "matrimonio", "Hochzeit", "结婚", "γἁμος"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_22, new String[]{"maison", "House", "hiša", "منزل", "maja", "casa", "casa", "кућа", "casa", "Haus", "房屋", "σπἱτι"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_23, new String[]{"famille", "family", "družina", "عائلة", "perekond", "familia", "família", "породица", "famiglia", "Familie", "家庭", "οικογἑνεια"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_24, new String[]{"foyer", "home", "dom", "مسكن", "kodu", "hogar", "lar", "дом", "casa", "Haushalt", "家", "οἱκος"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_25, new String[]{"homosexuel", "homosexual", "homoseksualec", "مثلي الجنس", "homoseksuaal", "homosexual", "homossexual", "хомосексуалац", "omosessuale", "homosexuel", "同性恋", "ομοφυλλὁφιλος"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_26, new String[]{"fusil", "gun", "pištola", "بندقية", "tulirelv", "pistola", "espingarda", "пиштољ", "fucile", "Gewehr", "步枪", "ντουφἑκι"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_27, new String[]{"cagoule", "balaclava", "roparska kapa", "قبعة قناع", "kapuuts", "pasamontañas", "capuz", "фанкомка", "passamontagna", "Kapuze", "仅露两眼的风帽", "κουκοὑλα"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_28, new String[]{"terroriste", "terrorist", "terorist", "إرهابي", "terrorist", "terrorista", "terrorista", "терориста", "terrorista", "Terrorist", "恐怖分子", "τρομοκρἁτης"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_29, new String[]{"policier", "police officer", "policaj", "شرطي", "politseinik", "policía", "policial", "полицајац", "carabiniere", "Polizist", "警察", "αστυνομικὁς"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_30, new String[]{"interpellation", "interpellation", "interpelacija", "إستجواب", "ülekuulamine", "interpelación", "interpelaçao", "испитивање", "arresto", "Festnahme", "传讯", "μεταγωγἡ"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_31, new String[]{"milice", "militia", "milica", "ميليشيا", "", "milicia", "milícia", "паравојна јединица", "milizia", "Bürgerwehr", "民兵", "στρατιωτικἑς δυνἁμεις"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_32, new String[]{"militaire", "military", "vojaški", "عسكري", "sõjaväelane", "militar", "militar", "војска", "militare", "Militär", "军人", "στρατιωτικὁς"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_33, new String[]{"triste", "sad", "žalosten", "حزين", "kurb", "triste", "triste", "тужан", "triste", "traurig", "伤心", "θλυμἑνος"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_34, new String[]{"heureux", "happy", "vesel", "سعيد", "õnnelik", "feliz", "feliz", "срећан", "felice", "glücklich", "高兴", "χαροὑμενος"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_35, new String[]{"colère", "anger", "jeza", "غاضب", "vihane", "enfadado", "zangado", "бесан", "arrabbiato", "wütend", "生气", "θυμωμἑνος"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_36, new String[]{"malade", "sick", "bolan", "مريض", "haige", "enfermo", "doente", "болестан", "malato", "krank", "病", "ἁρρωστος"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_37, new String[]{"peur", "fear", "strah", "خوف", "hirm", "miedo", "medo", "страх", "paura", "Angst", "怕", "φὁβος"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_38, new String[]{"école", "school", "šola", "مدرسة", "kool", "escuela", "escola", "школа", "scuola", "Schule", "学校", "σχολεἱο"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_39, new String[]{"tribunal", "court", "sodišče", "محكمة", "kohus", "corte", "tribunal", "суд", "corte", "Gericht", "法院", "δικαστἡριο"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_40, new String[]{"diplôme", "diploma", "diploma", "شهادة", "diplom", "diploma", "diploma", "диплома", "diploma", "Diplom", "证书", "δἱπλωμα"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_41, new String[]{"banque", "bank", "banka", "بنك", "pank", "banco", "banco", "банка", "banca", "Bank", "银行", "Τρἁπεζα"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_42, new String[]{"télévision", "TV", "televizija", "تلفزيون", "televisioon", "televisión", "televisao", "телевизор", "televisione", "Fernseher", "电视", "τηλεὁραση"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_43, new String[]{"formulaire", "form", "obrazec", "استمارة", "vorm", "forma", "fomulário", "формулар", "modulo", "Formular", "表格", "ἑντυπο"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_44, new String[]{"journaliste", "reporter", "novinar", "صحافي", "ajakirjanik", "periodista", "jornalista", "новинар", "giornalista", "Journalist", "记者", "δημοσιογρἁφος"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_45, new String[]{"tente", "tent", "šotor", "خيمة", "telk", "carpa / tienda campana", "tenda", "шатор", "tenda", "Zelt", "帐篷", "σκηνἡ"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_46, new String[]{"garage", "garage", "garaža", "مرأب", "garaaž", "garaje", "garagem", "гаража", "garage", "Werkstatt", "车库", "γκαρἁζ"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_47, new String[]{"refuge", "refuge", "zavetišče", "ملجأ", "varjupaik", "abrugi", "refúgio", "уточиште", "rifugio", "Zuflucht", "庇护所", "καταφὑγειο"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_48, new String[]{"dehors", "out", "zunaj", "خارج", "väljas", "fuera", "exterior", "напољу", "fuori", "außen", "外面", "ἑξω"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_49, new String[]{"maison", "House", "hiša", "بيت", "maja", "casa", "casa", "кућа", "casa", "Haus", "房屋", "σπἱτι"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_50, new String[]{"eau", "water", "voda", "ماء", "vesi", "agua", "água", "вода", "aqua", "Wasser", "水", "νερὁ"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_51, new String[]{"soif", "thirst", "žejen", "عطش", "janu", "sed", "sede", "жеђ", "sete", "Durst", "渴", "διψἁω"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_52, new String[]{"électricité", "electricity", "elektrika", "كهرباء", "elekter", "electricidad", "eletricidade", "струја", "elettricità", "Strom", "电", "ηλεκτρισμὁς"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_53, new String[]{"infirmière", "nurse", "medicinska sestra", "ممرضة", "medõde", "enfermera", "enfermeira", "медицинска сестра", "infermiera", "Krankenschwester", "护士", "νοσοκὁμα"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_54, new String[]{"docteur", "doctor", "zdravnik", "طبيب", "arst", "doctor", "médico", "доктор", "medico", "Arzt", "医生", "γιατρὁς"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_55, new String[]{"sida", "AIDS", "AIDS", "سيدا", "AIDS", "VIH", "sida", "СИДА", "HIV", "Aids", "艾滋病", "AIDS"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_56, new String[]{"ambulance", "ambulance", "rešilec", "سيارة إسعاف", "kiirabi", "ambulancia", "ambulância", "кола хитне помоћи", "ambulanza", "Ambulanz", "救护车", "ασθενοφὁρο"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_57, new String[]{"hôpital", "hospital", "bolnišnica", "مستشفى", "haigla", "hospital", "hospital", "болница", "ospedale", "Krankenhaus", "医院", "νοσοκομεἱο"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_58, new String[]{"piqure", "sting", "pik", "لسعة", "süst", "picadura", "injeçao", "жаока", "puntura", "Spritze", "打针", "ἑνεση"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_59, new String[]{"pilule", "pill", "kontracepcijska", "حبة دواء", "tablett", "píldora", "pílula", "пилула", "pillola", "Pille", "药丸", "χἁπι"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_60, new String[]{"béquille", "crutch", "bergla", "عكاز", "kark", "muleta", "muletas", "штака", "stampella", "Krücke", "拐杖", "μπαστοὑνι"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_61, new String[]{"soleil", "sun", "sonce", "شمس", "päike", "sol", "sol", "сунце", "sole", "Sonne", "太阳", "ἡλιος"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_62, new String[]{"pluie", "rain", "dež", "مطر", "vihm", "lluvia", "chuva", "киша", "pioggia", "Regen", "雨", "βροχἡ"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_63, new String[]{"orage", "storm", "nevihta", "عاصفة", "torm", "tormenta", "trovoada", "олуја", "tempesta", "Gewitter", "暴风雨", "καταιγἱδα"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_64, new String[]{"lever", "lift", "sončni vzhod", "فجر", "koit", "amanecer", "madrugada", "лифт", "alba", "Sonnenaufgang", "日出", "ξημἑρωμα"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_65, new String[]{"coucher", "sleep", "sončni zahod", "غروب", "loojang", "atardecer", "pôr do sol", "спавати", "tramonto", "Sonnenuntergang", "日落", "δὑση"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_66, new String[]{"température", "temperature", "temperatura", "درجة الحرارة", "temperatuur", "temperatura", "temperatura", "температура", "temperatura", "Temperatur", "温度", "θερμοκρασἱα"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_67, new String[]{"froid", "cold", "mraz", "برد", "külm", "frío", "frio", "хладноћа", "freddo", "kalt / Kälte", "冷", "κρὑο"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_68, new String[]{"manifestation", "demonstration", "dogodek", "مظاهرة", "meeleavaldus", "manifestación", "manifestaçao", "демонстрације", "evento", "Kundgebung", "示威游行", "διαμαρτυρἱα"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_69, new String[]{"parler", "speak", "govoriti", "تَكَلَّمَ", "rääkima", "hablar", "falar", "говорити", "parlare", "sprechen", "说话", "μιλἁω"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_70, new String[]{"journal", "newspaper", "dnevnik", "صحيفة", "ajakiri", "periódico", "jornal", "новине", "giornale", "Zeitung", "报纸", "εφημερἱδα"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_71, new String[]{"grève de la faim", "hunger strike", "gladovna stavka", "إضراب عن الطعام", "näljastreik", "huelgo de hambre", "greve de fome", "штрајк глађу", "sciopero della fame", "Hungerstreik", "绝食", "απεργεἱα πεἱνας"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_72, new String[]{"libérer", "free", "osvoboditi", "حرر", "vabastama", "liberar", "libertar", "слободан", "rilaciare", "befreien", "释放", "απελευθερὡνω"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_73, new String[]{"torture", "torture", "mučenje", "تعذيب", "piinamine", "tortura", "tortura", "мучење", "tortura", "Folter", "酷刑", "βασανηστἡριο"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_74, new String[]{"violence", "violence", "nasilje", "عنف", "vägivald", "violencia", "violência", "насиље", "violenza", "Gewalt", "暴力", "βἱα"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_75, new String[]{"aveugle", "blind", "slep", "أعمى", "pine", "ciego", "cego", "слеп", "cieco", "blind", "盲", "τυφλὁς"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_76, new String[]{"censure", "censorship", "cenzura", "رقابة", "tsensuur", "censura", "censura", "цензура", "censura", "Zensur", "查禁", "λογοκρισἱα"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_77, new String[]{"ligoté", "tied", "zvezan", "مربوط", "kinni seotud", "atado", "atado", "везан", "legato", "gefesselt", "捆绑", "δεμἑνος"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_78, new String[]{"blessé", "injured", "poškodovan", "مجروح", "haavatud", "herido", "ferido", "рањен", "ferito", "veletzt", "伤员", "τραυματισμἑνος"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_79, new String[]{"menotté", "handcuffed", "vkljenjen", "مكبل", "käeraudades", "esposado", "algemada", "у лисицама", "ammanettato", "Hanschellen angelegt", "戴手铐", "δεμἑνος με χειροπἑδες"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_80, new String[]{"mutilé", "disabled", "pohabljen", "معاق", "sandistatud", "mutilado", "mutilar", "инвалид", "mutilato", "verstümmelt", "残疾人", "ακρωτηριασμἑνος"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_81, new String[]{"emprisonné", "jailed", "zaprt", "مسجون", "vangistatud", "encarcelado", "preso", "ухапшен", "incarcerato", "gefangen", "管进了监狱", "φυλακισμἑνος"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_82, new String[]{"supplice", "torture", "mučenje", "تعذيب", "piinamine", "tortura", "suplício", "мучење", "supplizio", "Qual", "刑", "μαρτὐριο"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_83, new String[]{"séquestré", "locked", "zapreti", "المحتجزة", "pantvangis hoitud", "embargado", "sequestrado", "закључан", "sequestrato", "", "非法监禁", "υπὁ αναγκαστικὁ περιορισμὁ"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_84, new String[]{"camion", "truck", "kamion", "شاحنة", "veoauto", "camión", "camião", "камион", "camion", "Lastwagen", "卡车", "φορτηγὁ"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_85, new String[]{"bus", "bus", "avtobus", "حافلة", "buss", "bus", "autocarro", "аутобус", "bus", "Bus", "公共汽车", "λεωφορεἱο"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_86, new String[]{"bateau", "boat", "ladja", "باخرة", "laev", "barco", "barco", "брод", "barca", "Schiff", "船", "πλοἱο"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_87, new String[]{"vagabond", "vagrant", "potepuh", "متشرد", "kodutu", "vagabundo", "vagabundo", "луталица", "vagabondo", "Landstreicher", "流浪者", "στοὑς δρὁμους"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_88, new String[]{"frontière", "border", "meja", "حدود", "piir", "frontera", "fronteira", "граница", "frontiera", "Grenze", "边境", "σὑνορα"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_89, new String[]{"clandestin", "clandestine", "prikrito", "خفي", "illegaalne immigrant", "clandestino", "clandestino", "тајни", "clandestino", "blinder Passagier", "偷渡者", "λαθραἱος"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_90, new String[]{"noyade", "drowning", "utopitev", "غرق", "uppumine", "ahogamiento", "afogamento", "давити се", "affogamento", "Ertringen", "溺水", "πνιγμὁς"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_91, new String[]{"avion", "airplane", "letalo", "طائرة", "lennuk", "avión", "avião", "авион", "aero", "Flugzeug", "飞机", "αεροπλἁνο"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_92, new String[]{"ouvrier", "worker", "delavec", "عامل", "tööline", "obrero", "operário", "радник", "operaio", "Arbeiter", "工人", "εργἁτης"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_93, new String[]{"paysan", "peasant", "kmet", "مزارع", "talumees/farmer", "campesino", "camponês", "сељак", "contadino", "Bauer", "农民", "αγρὁτης"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_94, new String[]{"travail", "working", "delo", "عمل", "töö", "trabajo", "trabalho", "радити", "lavoro", "Arbeit", "工作", "δουλειἁ"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_95, new String[]{"brouette", "wheelbarrow", "samokolnica", "عربة يدوية", "käru", "carretilla", "carrinho de mão", "колица", "carriola", "Schubkarre", "独轮车", "χειρἁμαξα καρὀτσι"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons_96, new String[]{"outils", "tools", "orodja", "أدوات", "tööriistad", "herramientas", "ferramenta", "алат", "attrezzi", "Werkzeuge", "工具", "εργαλεἱα"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_01, new String[]{"attaché", "attached", "zavezan", "مرفق", "atašee", "unido", "atado", "закачено", "attaccato", "festgebunden", "", "δεμἑνος"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_02, new String[]{"afrique", "africa", "Afrika", "أفريقيا", "Aafrika", "Africa", "África", "Африка", "africa", "afrikanisch", "非洲", "Αφρικἡ"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_03, new String[]{"menottes", "handcuffs", "vklenjen", "أصفاد", "käerauad", "esposas", "algemas", "лисице", "manette", "Handschellen", "手铐", "χειροπἑδες"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_04, new String[]{"asie", "asia", "Azija", "آسيا", "Aasia", "Asia", "Ásia", "Азија", "asia", "Asien", "亚洲", "Ασἱα"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_05, new String[]{"explosion", "explosion", "eksplozija", "انفجار", "plahvatus", "explosión", "explosão", "експлозија", "esplosione", "Explosion", "爆炸", "ἑκρηξη"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_06, new String[]{"calendrier", "calendar", "koledar", "روزنامه", "kalender", "calendario", "calendário", "календар", "calendario", "Kalender", "日历", "ημερολὁγιο"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_07, new String[]{"largage", "dropping", "spuščanje", "إسقاط", "", "caída", "largada por avião", "испустити", "(lancio ?)", "Abwurf", "空投", "αποβολἡ λυμἁτων"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_08, new String[]{"attentat", "attempt", "atentat", "اعتداء", "atendaat", "intento", "atentado", "покушати", "attentato", "Attentat", "袭击事件", "βομβιστικἡ επἱθεση"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_09, new String[]{"téléphone", "phone", "telefon", "هاتف", "telefon", "teléfono", "telefone", "телефон", "telefono", "Telefon", "电话", "τηλἑφωνο"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_10, new String[]{"enfant", "child", "otrok", "طفل", "laps", "niño", "criança", "дете", "bambino", "Kind", "孩子", "παιδἱ"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_11, new String[]{"réseau", "network", "omrežje", "طفل", "telefonivõrk", "red", "rede", "мрежа", "rete", "Empfang", "电话网络", "τηλεφωνικὀ δἰκτυο"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_12, new String[]{"mort", "dead", "mrtev", "شبكة", "surm", "muerto", "morte", "мртав", "morto", "Tod / tot", "死人", "νεκρὁς"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_13, new String[]{"chien", "dog", "pes", "كلب", "koer", "perro", "cão", "пас", "cane", "Hund", "狗", "σκὑλος"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_14, new String[]{"courriel", "Email", "elektronska pošta", "بريد الإلكتروني", "post / e-mail", "correo", "E-mail", "електронска пошта", "mail", "eMail", "电子邮件", "μἑιλ"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_15, new String[]{"europe", "europe", "Evropa", "أوروبا", "Euroopa", "Europa", "europa", "Европа", "europa", "Europa", "欧洲", "Ευρὡπη"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_16, new String[]{"inondation", "flooding", "poplava", "فيضانات", "ujutus", "inundación", "inundação", "поплава", "alluvioni", "Überschwemmung", "水灾", "πλυμμἡρα"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_17, new String[]{"refuser", "refuse", "zavrniti", "رفض", "keelduma, tagasi lükkama", "rechazar", "recusar", "одбити", "rifiutare", "ablehnen", "拒绝", "αρνοὑμαι"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_18, new String[]{"bourse", "scholarship", "štipendija", "منحة", "toetus", "beca", "bolsa", "стипендија", "borsa", "Börse", "奖学金", "χρηματηστἡριο"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_19, new String[]{"gaz", "gas", "plin", "غاز", "gaas", "gas", "gaz", "бензин", "gas", "Gas", "气体", "αἑριο"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_20, new String[]{"arme", "weapon", "vojska", "سلاح", "relv", "arma", "arma", "оружје", "arma", "Waffe", "武器", "ὁπλο"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_21, new String[]{"médecin", "doctor", "zdravnik", "طبيب", "arst", "médico", "médico", "доктор", "medico", "Medikament", "医生", "γιατρὁς"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_22, new String[]{"feu", "fire", "ogenj", "نار", "tuli", "fuego", "fogo", "ватра", "fuoco", "Feuer", "火", "φωτιἁ"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_23, new String[]{"justice", "justice", "pravica", "عدالة", "õiglus", "justicia", "justiça", "правда", "giustizia", "Gerechtigkeit", "公正", "δικαιοσὑνη"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_24, new String[]{"mine", "mine", "rudnik", "منجم", "kaevandus", "mina", "mina", "мина", "miniera", "Aussehen", "矿", "νἁρκη"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_25, new String[]{"secours", "relief", "pomoč", "غاثة", "abi", "ayuda", "socorros", "помоћ", "soccorso", "Hilfe", "救助", "βοἡθεια"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_26, new String[]{"contrôle", "control", "kontrola", "مراقبة", "kontroll", "control", "controlo", "контрола", "controllo", "Kontrolle", "检查", "ἑλεγχος"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_27, new String[]{"musique", "music", "glasba", "موسيقى", "muusika", "música", "música", "музика", "musica", "Musik", "音乐", "μουσικἡ"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_28, new String[]{"apatride", "stateless", "brez državljanstva", "بدون جنسية", "", "apátrida", "apátrida", "без државе", "apolide", "Staatenlos", "无国籍的人", "ἁπατρις"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_29, new String[]{"amérique", "america", "Amerika", "أمريكا", "ameerika", "America", "América", "Америка", "america", "Amerika", "美洲", "Αμερικἡ"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_30, new String[]{"tente", "attempted", "šotor", "خيمة", "telk", "carpa / tienda campana", "tenda", "покушао", "tenda", "Zelt", "帐篷", "σκηνἠ"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_31, new String[]{"monde", "world", "svet", "عالم", "maailm", "mundo", "mundo", "свет", "mondo", "Welt", "世界", "κὁσμος"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_32, new String[]{"ecrire", "write", "pisati", "كتابة", "kirjutama", "escribir", "escrever", "писати", "scrivere", "schreiben", "写", "γρἁφω"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_33, new String[]{"geneve", "geneva", "Ženeva", "جنيف", "Genova", "Ginebra", "Genebra", "Женева", "ginevra", "Genf", "日内瓦", "Γενεὐη"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_34, new String[]{"colis", "package", "pošiljka", "طرد بريدي", "pakk", "paquete", "encomenda", "пакет", "pacco", "Paket", "邮包", "δἑμα"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_35, new String[]{"salaire", "salary", "plača", "راتب", "palk", "sueldo", "salário", "плата", "stipendio", "Gehalt", "工资", "μισθὁς"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_36, new String[]{"refugié", "refugee", "begunec", "لاجئ", "põgenik", "refugiado", "Refugiado", "избеглица", "profugo", "Zuflucht gesucht", "避难者", "πρὁσφυγας"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_37, new String[]{"santé", "health", "zdravje", "صحة", "tervis", "salud", "Saúde", "здравље", "salute", "Gesundheit", "健康", "υγεἱα"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_38, new String[]{"bateau", "boat", "ladja", "باخرة", "laev", "barco", "barco", "брод", "barca", "Boot", "船", "πλοἱο"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_39, new String[]{"vieux", "old", "star", "عجوز", "vana", "viejo", "velho", "стар", "vecchio", "alt", "老", "γἑρος"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_40, new String[]{"enceinte", "pregnant", "noseča", "حامل", "rase", "embarazada", "grávida", "трудна", "incinta", "schwanger sein", "怀孕", "ἑγγυος"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_41, new String[]{"prisonnier", "prisoner", "zapornik", "سجين", "vang", "prisionero", "Prisioneiro", "заробљеник", "prigionero", "Gefangener", "俘虏", "φυλακισμἑνος"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_42, new String[]{"camp", "camp", "zaporniški kamp", "مخيم", "vangilaager", "campo", "Acampamento", "камп", "campo carcerario", "Lager", "拘押营", "στρατὁπεδο συγκἑντρωσης"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_43, new String[]{"radio", "radio", "radio", "راديو", "raadio", "radio", "rádio", "радио", "radio", "Radio", "收音机", "ραδιὁφωνο"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_44, new String[]{"soldat", "soldier", "vojak", "جندي", "sõdur", "soldado", "Soldado", "војник", "soldato", "Soldat", "战士", "στρατιὡτης"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_45, new String[]{"document", "document", "dokumenti", "مستندات", "dokumendid", "documentos", "documentos", "документ", "documenti", "Papiere", "文件", "δημὁσια εγγραφα"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_46, new String[]{"riz", "rice", "riž", "أرز", "riis", "arroz", "Arroz", "пиринач", "riso", "Reis", "米饭", "ρὑζι"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_47, new String[]{"CRS", "police officer", "specialci", "شرطة مكافحة الشغب", "KAPO", "policía antidisturbios", "PSP", "полицајац", "forze mobili", "Spezialeinsatzkraft", "宪兵", "ΜΑΤ"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_48, new String[]{"route", "road", "cesta", "طريق", "tee", "carretera", "estrada", "пут", "strada", "Straße", "道路", "δρὁμος"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_49, new String[]{"douche", "shower", "tuš", "حمام", "dušš", "ducha", "duche", "туш", "doccia", "Dusche", "洗澡", "ντοὑζ"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_50, new String[]{"amerique du sud", "South America", "Južna amerika", "أمريكا الجنوبية", "Lõuna-Ameerika", "America del Sur", "América do Sul", "Јужна Америка", "sud america", "Südamerika", "南美洲", "Νὁτια Αμερικἡ"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_51, new String[]{"tétine", "pacifier", "duda", "لهاية", "lutt", "chupete", "chupeta", "цуцла", "ciuccio", "Schnuller", "奶嘴", "πιπἱλα"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_52, new String[]{"mirador", "observation tower", "opazovalni stolp", "رج المراقبة", "valvetorn", "torre de observación", "miradouro", "осматрачка кула", "torre di osservazione", "Wachturm", "瞭望塔", "πύργος παρατήρησης"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_53, new String[]{"casque", "helmet", "čelada", "خوذة", "kiiver", "casco", "capacete", "шлем", "casco", "Helm", "盔", "κρἁνος"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_54, new String[]{"village", "town", "vas", "قرية", "küla", "pueblo", "Aldeia", "град", "villaggio", "Dorf", "农村", "χωριὁ"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_55, new String[]{"handicap", "handicap", "prizadet", "إعاقة", "puudega", "handicap", "deficiência", "хендикеп", "disabilità", "Behinderung", "残障", "αναπηρἰα"}));
        pictoListAdapter.add(new Picto(R.drawable.all_icons2_56, new String[]{"wifi", "WIFI", "brezžična povezava", "واي فاي", "wifi", "wifi", "wi-fi", "бежични интернет", "wifi", "WLAN", "无线网络", "ασὑρματο ἰντερνετ"}));




        listView.setAdapter(pictoListAdapter);


        findViewById(R.id.home_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().add(R.id.drawer_layout, new About()).commit();
            }
        });

        findViewById(R.id.home_clear_canvas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.reset();
            }
        });

        successOverlay = findViewById(R.id.success_overlay);

        findViewById(R.id.home_save_canvas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.save(Home.this);
                successOverlay.setVisibility(View.VISIBLE);
                successOverlay.setAlpha(1.0f);
                successOverlay.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        successOverlay.animate().alpha(-1.0f).setDuration(800).start();
                    }
                }, 500);
            }
        });
        search = (EditText) findViewById(R.id.home_search);
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(search.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = search.getText().toString().toLowerCase(Locale.getDefault());
                pictoListAdapter.filter(text);

            }
        });
    }

    public Bitmap convertToBitmap(Drawable drawable, int widthPixels, int heightPixels) {
        Bitmap mutableBitmap = Bitmap.createBitmap(widthPixels, heightPixels, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mutableBitmap);
        drawable.setBounds(0, 0, widthPixels, heightPixels);
        drawable.draw(canvas);

        return mutableBitmap;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
