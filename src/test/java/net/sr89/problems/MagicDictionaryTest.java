package net.sr89.problems;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MagicDictionaryTest {
    MagicDictionary solution;

    @BeforeEach
    void setUp() {
        solution = new MagicDictionary();
    }

    @Test
    void testOrdering1() {
        solution.buildDict(new String[]{"fobyndk", "fo"});

        assertTrue(solution.search("bo"));
    }

    @Test
    void testOrdering2() {
        solution.buildDict(new String[]{"fo", "fobyndk"});

        assertTrue(solution.search("bo"));
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void runTests(Set<String> dictionary, Map<String, Boolean> searches) {
        solution.buildDict(dictionary.toArray(new String[0]));

        searches.forEach((searchTerm, expectedSearchResult) -> {
            boolean actual = solution.search(searchTerm);

            assertEquals(
                    expectedSearchResult,
                    actual,
                    () -> "Expected " + expectedSearchResult + " from searching " + searchTerm + " in dictionary: " + dictionary);
        });

    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(
                        Set.of("hello", "leetcode"),
                        Map.of(
                                "hello", false,
                                "hhllo", true,
                                "hell", false,
                                "leetcoded", false
                        )),
                Arguments.of(
                        Set.of("hello", "hallo", "leetcode"),
                        Map.of(
                                "hello", true,
                                "hhllo", true,
                                "hell", false,
                                "leetcoded", false
                        )),
                Arguments.of(
                        Set.of("fo", "fobyndk"),
                        Map.of(
                                "bo", true
                        )),
                Arguments.of(
                        Set.of("hqcefkkdtxvkokxupqheffemlcettmgmybmcjqd", "tqvuevvcqdbyganadubbzprtadbigvcbydwlwppklqqdmewfphkltyfvtlfwdmgfpslozkcmwelqmkrzwoldycmmccewecrwpvs", "figvqvkohvkiihihmdqpezubuqdjxenwzzrujkclpcyvdvhrdelhn", "fobyndkctmahjpjhiyaalgwovlxettyojlwudhhtdvasfdzypryz", "afqfkmxmtoficmaekaldghcbovyqoaajwrognzcsymlmuqiqqcawjklybteamknzpazhokofsfgwxjnindi", "hzwykjriueezwmnmyvxafchqicaqnbqrbsqnvmuaarlamzgwbt", "opvfxeqrijhxodcocboalfkjswpnxpykcltbucskwqokvabcpnncyxqknqaukwlsyfcymjopy", "svndbqiznkpayoqwqfrbkghvywzijxsxguzrzacnxldspwhtyvslyfkrfvxnwfqtwzkbgn", "svegroaaeymtrykymumvplqzxwsefkeslfwguaagiffegfsgsjudeocqfejruuyydmrkoemhfpgraslgyrkcfphmhwhrdwrle", "iigozeamuhmubqncozsztszzeagyghbwlhzdck", "kyqqcc", "wbvtywlnkdccmmkmpckergvuruaddvkpmsqrvtitjfzsecp", "fo", "ylnvlngvebfnng", "slpheqzaptkoubtviwnmjhtvfcsreysiuzpylkdnzznlaxutigpptqeqifqjhrftnfns", "ykqehgfczn", "tcqxjvavirvzukhyhjnwykuzyarrlrmaycxemqhgzemokoypmil", "uprqmgudyfefasmbqywvcugdqujijzsuboifgbktbgxswbrlxbiuvbogdqyzfxnvmmifrosruzvdrhtlxfwfzjikvplvwh", "memyndggyvsqjdntrgxecgrkmcmrkkfjdasxhgr", "lnahczutqyhwumbapructp", "motrdfychlrurepgnqvprhunwiwmrhajatpxjjdvdtmtexiwmksvbtdnzqqftrnzqdscpepkyjdsgmeqdztrl", "vedyymdprufufokvxwdaoaiidzighghuryoineyycrhdxrdkxdgmaahq", "fizycbajzqifsjtrzymoenxrkgsrxgrblmzentdui", "jqgzwm", "rnxqkwyridhfwlgkdrmgxcoodqnkauplbhuxmmwgocnoumtemebikopkq", "maepukvxnieiudrguuvjqefwcpopxezjaxzeaqramwmchbvvpwjmcjaknqbjfxjonjmghpudqcckxmfrxzdjeqmgmitxpc", "uvakofoanddmrunpbwyqxhlrdarkohxphvtvckdnmfplpjuxogwsgrwvftyaawlwtftzzmfbeubkpnysyvd", "aipwbfwavlkkkicuyvuweojuqsiulnchunbobpohrkujnekegegvylnvgcahckwnurtmxkmutdmikorjmguqxkd", "mjzdcybukypmqhqgpjqfr", "dxbvdcxvemjkujnpjqfeigejzgkfsowxegbrrlseylxczbxvrzlnymopgyvlzmyannsgxjnvguosgeimauym", "zignfufvuereucrzbzhinoqbyegnntsfpgewzrssbgag", "evyeskltiibijggujjjmxyetqyfpowpfvauiuzwzfsujmlevgaqzd", "tlovkhoxtmxzlnoqzvtyuxekjyolh", "dqibutwieciegwazbylekyvhnepwlwrsjcdupiivnfbyudwrcytefrtckzwbvqexdpvjwhqdfjcjhxyfpljyupdjdslstxwzajf", "rmuawruoozzgkhavbrfguqukjpqgcrttbcwkwyvfannqkkdwmakjkwynmftcsirrriwuovdmanobrmvafwllihw", "lciuhohzmlpknvxcnvutukbhyeqzlopspkopefoacwnfgvdvywuepvbkbxverqwfqmmqelrffdzcojelnhfagbhuiuaqzdokt", "ikaczbhltbtvybhuoegdtbfhmelxhcepbiegursieuhjrjvcsjdynpljumxfzvuxzahmnfrvdfwdngcmwzzzcpynjanxffwsjit", "kevxuounfbplmpgesdcrsogibccvnxzogntxhzmmnjomadkchlmyhrkwuivcphgrgot", "vqbajkmysnlowqgcuqfbfsgidnlrattafbqemcnnbejnykhxxopexfzlqnwlboparbgfajnhjian", "mldnittgqyngzcwumffnjkmnwerryligmoihinxrapdjsfwufsleatlxlfrookaivdvxy", "ckbaywevzrayotbyugvvlyrptjetekrkudclafrqkzyocndxodmjkyqcwxxicuomppifqhdcoguzzmppn", "sbbagyuhzuvywflbirezefxxeo", "yyhrkjdngsyfcrqlgkyqcqkchhxqwjspgnrxwubhaetmxfmhwevlefgabozliipxgxxloq", "bixqbrljjijkwypdoicofvjavoqyuznapiqwmrxbbvmu", "bbuqcrvyyauhyeafjyahheklalhasbohbossoivhyivjknlfi", "hxkvflrwafqgjrbnkgbginxusbzzjospayoclavlhupaqghanqvygxahzzyspyued", "ybsemhouwzsgoqagiynodiayeaengqedtqobhyupmafjgxnvfdhfrephtaqedjkvjwnfulx", "fszdvdripirczluoahwktyozjqyhwllnppjhgyoycunjkuiqldmmpxezsigxmcjveinlqnpjhkxfbhuelhkvrknl", "wwactkdvlzzelfmxhycjgbmdzmptrclhxhvdnfjxzuhnoebjcdqktxamshdrqzjhhj", "qckjulixfmlezxaompowzzzzammrblsziuwsksjzfoyieeapxkqrxznlxiyiahxosoqauivkygzehhikzi", "ytajkptmjwjkbehixuxuioj", "bslhokzotyjhgeodrnkd", "trg", "iyoqrzttwixxrikrm"),
                        crazy())
        );
    }

    private static Map<String, Boolean> crazy() {
        final var map = new HashMap<String, Boolean>();

        map.put("hhltkrmdicfxnykkjuaocnvbiuoaoipbxzhqmva", false);
        map.put("ylnvlngvebfang", true);
        map.put("nrkdwsfqxyvrltdgydegecdovqjoehmsxrzebjhvivknfxnstqabvakskfxnbxtoqyllrjogewtmtffnuozailbqdetvfmyibgj", false);
        map.put("fhbyndkctmahjpjhiyaalgwovlxettyojlwudhhtdvasfdzypryz", true);
        map.put("rzewrskskzuwfsrlbujbmgagcocvdvqahlgkeqltyiky", false);
        map.put("ckbaywevzrayotbyunvvlyrptjetekrkudclafrqkzyocndxodmjkyqcwxxicuomppifqhdcoguzzmppn", true);
        map.put("bocpmstngeguvpnuerlaqeijqopuoarvpthwscfpmxveofaaksqrooqdasjmhjfhpcznuyfjwkznopueabrglrluivgf", false);
        map.put("sbbagyuhzuvywfibirezefxxeo", true);
        map.put("maepukvxnieiudrmuuvjqefwcpopxezjaxzeaqramwmchbvvpwjmcjaknqbjfxjonjmghpudqcckxmfrxzdjeqmgmitxpc", true);
        map.put("sioledzfaeqwlnnjjnhgagpekkksfrn", false);
        map.put("opvfxeqrijhxodcocboalfkjswpnxpykcltbucskwqokvabcpnncyxqkkqaukwlsyfcymjopy", true);
        map.put("hhfempskcdglbshdbcyslwbralqwhpzvquehuarcqnxuceupegjazouzljhvgbboxqhbzzzhvy", false);
        map.put("memyndggyvsqjdntrgxecgrkmcmrskfjdasxhgr", true);
        map.put("uayxmnuyociyhiyyricsatsptkgbytxcyuolhzfrsbfjpmfzqswsgdpzfmmzirl", false);
        map.put("lwtsznzxtj", false);
        map.put("tcg", true);
        map.put("lciuhohzmlpknvxcnvutukbhyeqzlopspkopefoacwnfgvdvywueavbkbxverqwfqmmqelrffdzcojelnhfagbhuiuaqzdokt", true);
        map.put("yyhrkjdngsyfcrqlgkyqcqkchhxqwjspgnrxwubhmetmxfmhwevlefgabozliipxgxxloq", true);
        map.put("fg", true);
        map.put("jqgswm", true);
        map.put("qckjulixfmlezxoompowzzzzammrblsziuwsksjzfoyieeapxkqrxznlxiyiahxosoqauivkygzehhikzi", true);
        map.put("mjzdcybykypmqhqgpjqfr", true);
        map.put("qckjulixfmlezxaompowzzzzammrblsziuwsksjzfoyieeapxmqrxznlxiyiahxosoqauivkygzehhikzi", true);
        map.put("ytajkptmjwjibehixuxuioj", true);
        map.put("ytajkptmjwjkbeyixuxuioj", true);
        map.put("otwrtmyuqgfbhwtpygmomaxfzkwpvrkpapzmcwyqxzygrafcxhsiuzagdljcvktxqwfmmfhvlxmaxslle", false);
        map.put("dqibutwieciegwazbylekyvhnepwlwrsjcdupiivnfbyudwrcytefrtckzwbvqexdpvgwhqdfjcjhxyfpljyupdjdslstxwzajf", true);
        map.put("sahbfdvnivdvz", false);
        map.put("xwnhovrbfxqijfpwbasqvjnkdjlxwfytlbewjqxaajrqwmonynupredyp", false);
        map.put("iyhqrzttwixxrikrm", true);
        map.put("cjjcutywbuvdnjsmfiurrkrxzlxfdpcoflowrbmgcjrdsgcthgfppbndrtzxbqujnvxntyowbuatusinyenlzk", false);
        map.put("s", false);
        map.put("rnxqkwyridhfwltkdrmgxcoodqnkauplbhuxmmwgocnoumtemebikopkq", true);
        map.put("pcymmqcdunbznsspvslfatufstdgzizppvonovgvoimnbdhkghadrdth", false);
        map.put("ybsemhouwzsgoqagiynodiayeaengqedtqobhyupmafjgxnvfdhfrephtaqedjkvjwnfult", true);
        map.put("maepukvxnieiudrguuvjqefwcpopxezjaxzeaqramwmchbvvpwjmcjaknqbjfxsonjmghpudqcckxmfrxzdjeqmgmitxpc", true);
        map.put("wwactkdvlzzelfmxhycjgbmdzmptrclhxhvdnfjxzuhnoebjcdqktxamshdrozjhhj", true);
        map.put("sbbaayuhzuvywflbirezefxxeo", true);
        map.put("bidqbrljjijkwypdoicofvjavoqyuznapiqwmrxbbvmu", true);
        map.put("tjcmuljgdrhommsjqbhbqikposuzffidetcbbdpmdfzndusazijyirwladzsdoivvshjhaudpvnjpacqvq", false);
        map.put("dqibutwieciegwazbylekyvhnepwlwrsjcdupiivnfbyudwrcytefrtckzwbvqhxdpvjwhqdfjcjhxyfpljyupdjdslstxwzajf", true);
        map.put("gkajhwwwdjywpbtgxioatcqadzmno", false);
        map.put("afqfkmxmtoficmaekaldghcbovyqoaajwrognzcsymqmuqiqqcawjklybteamknzpazhokofsfgwxjnindi", true);
        map.put("kevxuounfbplmpgesdcrsogibccvnxzogntxhzmmnjomalkchlmyhrkwuivcphgrgot", true);
        map.put("figvqvkohvkiihihmdqpezubuqdjxenwzzrujkclccyvdvhrdelhn", true);
        map.put("vqbajkmysnlowqgcuqfbfsgidnlrattafbqemcnnxejnykhxxopexfzlqnwlboparbgfajnhjian", true);
        map.put("iyoqrzttwdxxrikrm", true);
        map.put("fszdvdripirczluoahwktyozjqyhwllnpzjhgyoycunjkuiqldmmpxezsigxmcjveinlqnpjhkxfbhuelhkvrknl", true);
        map.put("fizycfajzqifsjtrzymoenxrkgsrxgrblmzentdui", true);
        map.put("fszdvdripirczluoahwktyozjqyhwllnppjhgyoycunjkuiqldmmpxezsigxmcjvdinlqnpjhkxfbhuelhkvrknl", true);
        map.put("lftfdvlcurtbpkznwiyyiuwsgutlgvquqashatkeikedxorhlkiwvz", false);
        map.put("maepukvanieiudrguuvjqefwcpopxezjaxzeaqramwmchbvvpwjmcjaknqbjfxjonjmghpudqcckxmfrxzdjeqmgmitxpc", true);
        map.put("opvfxeqrijhsodcocboalfkjswpnxpykcltbucskwqokvabcpnncyxqknqaukwlsyfcymjopy", true);
        map.put("hxkvflrwafqgjrbnkgbginxusbzzjospayociavlhupaqghanqvygxahzzyspyued", true);
        map.put("kdzjxbvfiwzifffxnbmixmqzmoonbisktiocuicxjvcxxnaidltshuppcdbkoqqivexrhmtxxvo", false);
        map.put("opvfxeqrijhxodcocboalfkjswpnxpykcltbucskwqokvabxpnncyxqknqaukwlsyfcymjopy", true);
        map.put("tlovkhoxtmxzlnoqzvtyuxewjyolh", true);
        map.put("rnxqkwyridhfwlgkdrmgxcoodfnkauplbhuxmmwgocnoumtemebikopkq", true);
        map.put("zignfufuuereucrzbzhinoqbyegnntsfpgewzrssbgag", true);
        map.put("lnahczutqyhwumbaprucyp", true);
        map.put("svndbqiznkpayoqwqfrikghvywzijxsxguzrzacnxldspwhtyvslyfkrfvxnwfqtwzkbgn", true);
        map.put("xcwasxjefegzjfzfgkjcnzeirspffkzbavstubgnddmwvojnyokdxxpsomb", false);
        map.put("memyndggyvsqqdntrgxecgrkmcmrkkfjdasxhgr", true);
        map.put("wrzcodydztzp", false);
        map.put("dqibutwieciegwazbylekyehnepwlwrsjcdupiivnfbyudwrcytefrtckzwbvqexdpvjwhqdfjcjhxyfpljyupdjdslstxwzajf", true);
        map.put("evyeskltiibijggujjjmxyetqyfpowpfvauiuzwzfsujmleveaqzd", true);
        map.put("hxkvflrwafqgjrbnkgbginxusbzzjospayoolavlhupaqghanqvygxahzzyspyued", true);
        map.put("vqbajkmysnlowqgcuqfbfsgidnlrtttafbqemcnnbejnykhxxopexfzlqnwlboparbgfajnhjian", true);
        map.put("qckjulixfmlezxaompowzzzzammrblsziuwsksjzfoyieeapxkqrxznlxiyiahxosozauivkygzehhikzi", true);
        map.put("euecumzbjqnvtybaawriiakodbtenqrjpna", false);
        map.put("fobyndkctmahjpjhiyaalgwovlxettyojlwuzhhtdvasfdzypryz", true);
        map.put("vagzsjbrguvmgnnnjeooweqvtjfcvjjkqnejgxxrbijkewtdblwmwrwetfsv", false);
        map.put("bbuqcrvyyauhyeafjyahheklblhasbohbossoivhyivjknlfi", true);
        map.put("aipwbfwbvlkkkicuyvuweojuqsiulnchunbobpohrkujnekegegvylnvgcahckwnurtmxkmutdmikorjmguqxkd", true);
        map.put("kwgadrsgsllephwryorwskjmevfloldxpakorphexhajfrmfailmiikmtoud", false);
        map.put("fh", true);
        map.put("bo", true);
        map.put("xjmpcvcfmaedrqbvgrdontdcmprhacwbgabtsxmzmcroozskaapmqesgeigobklrxjckljebpjfnekpjzdvkscnqgopacchwdtaj", false);
        map.put("zqgzwm", true);
        map.put("sbbagyuhzuvywflbireztfxxeo", true);
        map.put("whlilcgorpjdg", false);
        map.put("tqvuevvcqdbyoanadubbzprtadbigvcbydwlwppklqqdmewfphkltyfvtlfwdmgfpslozkcmwelqmkrzwoldycmmccewecrwpvs", true);
        map.put("sdjfsmbwxyotxqkoafkalcyhxhojmqmmkznjjpr", false);
        map.put("dxbvdcxvemjkujnpjqfeigejzgkfsowxegbrrlseylxczbxvrzlnymopgyvlzmyannsgxjnvgyosgeimauym", true);
        map.put("vedyymdprufufokvxwdaoaiiizighghuryoineyycrhdxrdkxdgmaahq", true);
        map.put("jyfbigywzeccg", false);
        map.put("mldnittgqyngzcwumffnjkmnwerryligmoihinxrapdjsfwufsleatlxmfrookaivdvxy", true);
        map.put("ytajkptmjwjkbehfxuxuioj", true);
        map.put("tcqxjvavirvzukhyhjnwykizyarrlrmaycxemqhgzemokoypmil", true);
        map.put("maepukvxnieiudrguuvjqefwcpopxezjaxzeaqramwmchbvvpwjmcjaknqbjfxjonjmghpudqcckxmfwxzdjeqmgmitxpc", true);
        map.put("evyeskltiibijggujjjmxyetqyfpowpfvxuiuzwzfsujmlevgaqzd", true);
        map.put("kyqmcc", true);
        map.put("tcqxjvavirvzukhyhjnwykuzyazrlrmaycxemqhgzemokoypmil", true);
        map.put("crg", true);
        map.put("qhsfmhttfasparnsepcrtjzejxybmhpgddaqdprwcfdioseeg", false);
        map.put("sbbagyuhzuaywflbirezefxxeo", true);
        map.put("rsqcnhtypfbdxjfkyfcrnegnhohvugswmxcyvhuikoameezdqiy", false);
        map.put("jgbajgarhdemijtzdlfitdduuvkteftelgsuzbbfxrbqzhs", false);
        map.put("figvqvkohvkiihihmdqpezubuqdjxenwzzrujkclgcyvdvhrdelhn", true);

        return map;
    }
}