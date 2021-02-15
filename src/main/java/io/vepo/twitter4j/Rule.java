package io.vepo.twitter4j;

public class Rule {
    @SuppressWarnings("unchecked")
    private static abstract class AbstractMatchingRuleBuilder<T extends AbstractMatchingRuleBuilder<?>> {
        protected StringBuilder value;

        private AbstractMatchingRuleBuilder(Class<T> thisClass) {
            value = new StringBuilder();
        }

        public T isRetweet() {
            this.value.append(" is:retweet");
            return (T) this;
        }

        public T or() {
            this.value.append(" OR");
            return (T) this;
        }

        public T with(Matching matching) {
            this.value.append(" (");
            this.value.append(matching.value);
            this.value.append(")");
            return (T) this;
        }

        public T withImages() {
            this.value.append(" has:images");
            return (T) this;
        }

        public T withLanguage(Language language) {
            this.value.append(" lang:").append(language.lang);
            return (T) this;
        }

        public T withLinks() {
            this.value.append(" has:links");
            return (T) this;
        }

        public T withMedia() {
            this.value.append(" has:media");
            return (T) this;
        }

        public T withMentions() {
            this.value.append(" has:mentions");
            return (T) this;
        }

        public T without(Matching matching) {
            this.value.append(" -(");
            this.value.append(matching.value);
            this.value.append(")");
            return (T) this;
        }

        public T withToken(String token) {
            if (token.trim().contains(" ")) {
                this.value.append(" \"").append(token).append('"');
            } else {
                this.value.append(" ").append(token);
            }
            return (T) this;
        }
    }

    public enum Language {
        Amharic("am"),
        Arabic("ar"),
        Armenian("hy"),
        Basque("eu"),
        Bengali("bn"),
        Bosnian("bs"),
        Bulgarian("bg"),
        Burmese("my"),
        Catalan("ca"),
        Croatian("hr"),
        Czech("cs"),
        Danish("da"),
        Dutch("nl"),
        English("en"),
        Estonian("et"),
        Finnish("fi"),
        French("fr"),
        Georgian("ka"),
        German("de"),
        Greek("el"),
        Gujarati("gu"),
        Haitian_Creole("ht"),
        Hebrew("iw"),
        Hindi("hi"),
        Hungarian("hu"),
        Icelandic("is"),
        Indonesian("in"),
        Italian("it"),
        Japanese("ja"),
        Kannada("kn"),
        Khmer("km"),
        Korean("ko"),
        Lao("lo"),
        Latinized_Hindi("hi-Latn"),
        Latvian("lv"),
        Lithuanian("lt"),
        Malayalam("ml"),
        Maldivian("dv"),
        Marathi("mr"),
        Nepali("ne"),
        Norwegian("no"),
        Oriya("or"),
        Panjabi("pa"),
        Pashto("ps"),
        Persian("fa"),
        Polish("pl"),
        Portuguese("pt"),
        Romanian("ro"),
        Russian("ru"),
        Serbian("sr"),
        Simplified_Chinese("zh-CN"),
        Sindhi("sd"),
        Sinhala("si"),
        Slovak("sk"),
        Slovenian("sl"),
        Sorani_Kurdish("ckb"),
        Spanish("es"),
        Swedish("sv"),
        Tagalog("tl"),
        Tamil("ta"),
        Telugu("te"),
        Thai("th"),
        Tibetan("bo"),
        Traditional_Chinese("zh-TW"),
        Turkish("tr"),
        Ukrainian("uk"),
        Urdu("ur"),
        Uyghur("ug"),
        Vietnamese("vi"),
        Welsh("cy");

        private String lang;

        Language(String lang) {
            this.lang = lang;
        }

    }

    public static class Matching {
        public static MatchingRuleBuilder builder() {
            return new MatchingRuleBuilder();
        }

        private String value;

        public Matching(String value) {
            this.value = value;
        }

    }

    public static class MatchingRuleBuilder extends AbstractMatchingRuleBuilder<MatchingRuleBuilder> {

        private MatchingRuleBuilder() {
            super(MatchingRuleBuilder.class);
        }

        public Matching build() {
            return new Matching(this.value.toString()
                                          .trim());
        }
    }

    public static class RuleBuilder extends AbstractMatchingRuleBuilder<RuleBuilder> {

        private RuleBuilder() {
            super(RuleBuilder.class);
        }

        public Rule applyTag(String tag) {
            return new Rule(value.toString().trim(), tag);
        }

    }

    public static RuleBuilder builder() {
        return new RuleBuilder();
    }

    private final String value;

    private final String tag;

    private Rule(String value, String tag) {
        this.value = value;
        this.tag = tag;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Rule other = (Rule) obj;
        if (tag == null) {
            if (other.tag != null)
                return false;
        } else if (!tag.equals(other.tag))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

    public String getTag() {
        return tag;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((tag == null) ? 0 : tag.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Rule [value=" + value + ", tag=" + tag + "]";
    }

}
