
@param String modalConfirmed

@template.layout(content = @`
    <div hx-trigger="modalConfirmed from:body" hx-get="/modal-dialogs/client-side-event" hx-target="#client-side-event">
        <button
                hx-get="/modal-dialogs"
                hx-target="#modals-here"
                hx-trigger="click"
                class="btn primary">Open Modal</button>

        <div id="modals-here"></div>


        <div id="alert">${modalConfirmed}</div>
        <div id="client-side-event"></div>
    </div>
`,
footer = @`
    <span class="element"><a href="https://mastodon.social/@atomfrede">@atomfrede@mastodon.social</a></span>
    <span class="footer-element"><a href="https://javaforumnord.de/2025/">#jfn25</a></span>
    <span class="footer-element"><a href="https://codeberg.org/atomfrede/htmx-samples">source</a></span>
`)