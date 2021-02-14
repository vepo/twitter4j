package io.vepo.twitter4j;

import static java.util.Objects.nonNull;

public class Rule {
    public enum Language {
        Portuguese("pt"), English("en");

        private String lang;

        Language(String lang) {
            this.lang = lang;
        }

    }

    public static class RootGroupRuleBuilder extends SubGroupRuleBuilder {

        private RootGroupRuleBuilder(RuleBuilder parent) {
            super(parent);
        }

        @Override
        public RuleBuilder end() {
            return (RuleBuilder) super.end();
        }

        @Override
        public SubGroupRuleBuilder group() {
            return super.group();
        }

        @Override
        public RootGroupRuleBuilder isRetweet() {
            return (RootGroupRuleBuilder) super.isRetweet();
        }

        @Override
        public RootGroupRuleBuilder withImages() {
            return (RootGroupRuleBuilder) super.withImages();
        }

        @Override
        public RootGroupRuleBuilder withLinks() {
            return (RootGroupRuleBuilder) super.withLinks();
        }

        @Override
        public RootGroupRuleBuilder withMedia() {
            return (RootGroupRuleBuilder) super.withMedia();
        }

        @Override
        public RootGroupRuleBuilder withMentions() {
            return (RootGroupRuleBuilder) super.withMentions();
        }

        @Override
        public SubGroupRuleBuilder withoutGroup() {
            return super.withoutGroup();
        }
        

        @Override
        public RootGroupRuleBuilder withToken(String token) {
            return (RootGroupRuleBuilder) super.withToken(token);
        }
    }

    public static class RuleBuilder extends SubRuleBuilder {
        private Language language;

        private RuleBuilder() {
            super();
            this.language = null;
        }

        public Rule applyTag(String tag) {
            if (nonNull(language)) {
                this.value.append(" lang:").append(this.language.lang);
            }
            return new Rule(value.toString().trim(), tag);
        }

        @Override
        public RootGroupRuleBuilder group() {
            this.value.append(" (");
            return new RootGroupRuleBuilder(this);
        }

        @Override
        public RuleBuilder isRetweet() {
            return (RuleBuilder) super.isRetweet();
        }

        @Override
        public RuleBuilder or() {
            return (RuleBuilder) super.or();
        }

        @Override
        public RuleBuilder withImages() {
            return (RuleBuilder) super.withImages();
        }

        public RuleBuilder withLanguage(Language language) {
            this.language = language;
            return this;
        }

        @Override
        public RuleBuilder withLinks() {
            return (RuleBuilder) super.withLinks();
        }

        @Override
        public RuleBuilder withMedia() {
            return (RuleBuilder) super.withMedia();
        }

        @Override
        public RuleBuilder withMentions() {
            return (RuleBuilder) super.withMentions();
        }

        @Override
        public RootGroupRuleBuilder withoutGroup() {
            this.value.append(" -(");
            return new RootGroupRuleBuilder(this);
        }

        @Override
        public RuleBuilder withToken(String token) {
            return (RuleBuilder) super.withToken(token);
        }

    }

    public static class SubGroupRuleBuilder extends SubRuleBuilder {
        private SubRuleBuilder parent;

        private SubGroupRuleBuilder(SubRuleBuilder parent) {
            super();
            this.parent = parent;
        }

        public SubRuleBuilder end() {
            this.parent.value.append(this.value);
            this.parent.value.append(")");
            return parent;
        }

        @Override
        public SubGroupRuleBuilder isRetweet() {
            return (SubGroupRuleBuilder) super.isRetweet();
        }

        @Override
        public SubGroupRuleBuilder withImages() {
            return (SubGroupRuleBuilder) super.withImages();
        }

        @Override
        public SubGroupRuleBuilder withLinks() {
            return (SubGroupRuleBuilder) super.withLinks();
        }

        @Override
        public SubGroupRuleBuilder withMedia() {
            return (SubGroupRuleBuilder) super.withMedia();
        }

        @Override
        public SubGroupRuleBuilder withMentions() {
            return (SubGroupRuleBuilder) super.withMentions();
        }

        @Override
        public SubGroupRuleBuilder withoutGroup() {
            return super.withoutGroup();
        }

        @Override
        public SubRuleBuilder withToken(String token) {
            return super.withToken(token);
        }
    }

    public static class SubRuleBuilder {
        protected StringBuilder value;

        private SubRuleBuilder() {
            value = new StringBuilder();
        }

        public SubGroupRuleBuilder group() {
            this.value.append(" (");
            return new SubGroupRuleBuilder(this);
        }

        public SubRuleBuilder isRetweet() {
            this.value.append(" is:retweet");
            return this;
        }

        public SubRuleBuilder or() {
            this.value.append(" OR ");
            return this;
        }

        public SubRuleBuilder withImages() {
            this.value.append(" has:images");
            return this;
        }

        public SubRuleBuilder withLinks() {
            this.value.append(" has:links");
            return this;
        }

        public SubRuleBuilder withMedia() {
            this.value.append(" has:media");
            return this;
        }

        public SubRuleBuilder withMentions() {
            this.value.append(" has:mentions");
            return this;
        }

        public SubGroupRuleBuilder withoutGroup() {
            this.value.append(" -(");
            return new SubGroupRuleBuilder(this);
        }

        public SubRuleBuilder withToken(String token) {
            if (token.trim().contains(" ")) {
                this.value.append(" \"").append(token).append('"');
            } else {
                this.value.append(" ").append(token);
            }
            return this;
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
