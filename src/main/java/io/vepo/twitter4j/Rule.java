package io.vepo.twitter4j;

public class Rule {

    public static class RootGroupRuleBuilder extends SubGroupRuleBuilder {

        private RootGroupRuleBuilder(RuleBuilder parent) {
            super(parent);
        }

        @Override
        public RuleBuilder endGroup() {
            return (RuleBuilder) super.endGroup();
        }

        @Override
        public SubGroupRuleBuilder with() {
            return super.with();
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
        public SubGroupRuleBuilder without() {
            return super.without();
        }

        @Override
        public RootGroupRuleBuilder withToken(String token) {
            return (RootGroupRuleBuilder) super.withToken(token);
        }
    }

    public static class RuleBuilder extends SubRuleBuilder {
        private RuleBuilder() {
            super();
        }

        public Rule applyTag(String tag) {
            return new Rule(value.toString().trim(), tag);
        }

        @Override
        public RuleBuilder or() {
            return (RuleBuilder) super.or();
        }

        @Override
        public RootGroupRuleBuilder with() {
            this.value.append(" (");
            return new RootGroupRuleBuilder(this);
        }

        @Override
        public RuleBuilder withImages() {
            return (RuleBuilder) super.withImages();
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
        public RootGroupRuleBuilder without() {
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

        public SubRuleBuilder endGroup() {
            this.parent.value.append(this.value);
            this.parent.value.append(")");
            return parent;
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
        public SubGroupRuleBuilder without() {
            return super.without();
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

        public SubRuleBuilder or() {
            this.value.append(" OR ");
            return this;
        }

        public SubGroupRuleBuilder with() {
            this.value.append(" (");
            return new SubGroupRuleBuilder(this);
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

        public SubGroupRuleBuilder without() {
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
