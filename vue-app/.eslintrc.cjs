module.exports = {
    extends: [
        'eslint:recommended',
        'plugin:vue/vue3-recommended'
    ],
    rules: {
        "indent": ["error", 4],
        "semi": ["error", "always"],
        "semi-spacing": ["error", {
            "before": false,
            "after": true
        }],
        "comma-spacing": ["error", {
            "before": false,
            "after": true
        }],
        "comma-dangle": ["error", "never"],
        "func-call-spacing": ["error", "never"],
        "dot-location": ["error", "property"],
        "object-curly-newline": ["error", {
            "minProperties": 1
        }],
        "object-curly-spacing": ["error", "always"],
        "template-curly-spacing": ["error", "never"],
        "vue/comma-dangle": ["error", "never"],
        "vue/comma-spacing": ["error", {
            "before": false,
            "after": true
        }],
        "vue/dot-location": ["error", "property"],
        "vue/func-call-spacing": ["error", "never"],
        "vue/object-curly-spacing": ["error", "always"],
        "vue/template-curly-spacing": ["error", "never"],
        "vue/object-curly-newline": ["error", {
            "minProperties": 1
        }],
        "vue/html-indent": ["error", 4],
        "vue/script-indent": ["error", 4],
        "vue/require-default-prop": "off"
    }
};