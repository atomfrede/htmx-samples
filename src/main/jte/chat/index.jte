@template.layout(content = @`
    <main class="container" hx-ext="sse">
        <article class="secondary" id="message-box">
            <!-- Messages are displayed here -->

        </article>
    </main>
    @template.chat.input()
`)
