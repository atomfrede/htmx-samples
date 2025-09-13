@import org.codeberg.atomfrede.htmx_samples.FormModel
@import org.springframework.validation.BindingResult

@param FormModel formModel
@param BindingResult validationResult

@template.layout(content = @`
    @template.form(formModel = formModel, validationResult = validationResult)
`,
footer = @`
    <span class="element"><a href="https://mastodon.social/@atomfrede">@atomfrede@mastodon.social</a></span>
    <span class="footer-element"><a href="https://javaforumnord.de/2025/">#jfn25</a></span>
    <span class="footer-element"><a href="https://codeberg.org/atomfrede/htmx-samples">source</a></span>
`)
