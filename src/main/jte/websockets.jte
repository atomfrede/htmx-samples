@template.layout(content = @`
    <ul hx-ext="ws" ws-connect="/websockets/ws" id="news-feed"></ul>
`,
footer = @`
    <span class="footer-element"><a href="https://mastodon.social/@atomfrede">@atomfrede@mastodon.social</a></span>
    <span class="footer-element"><a href="https://javaforumnord.de/2025/">#jfn25</a></span>
    <span class="footer-element"><a href="https://codeberg.org/atomfrede/htmx-samples">source</a></span>
`)
