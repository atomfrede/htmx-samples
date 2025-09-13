@param java.util.UUID streamIdentifier
<article class="llm-message" aria-busy="true" id="llm-answer-${streamIdentifier.toString()}" hx-swap-oob="beforeend:#message-box" sse-connect="/llm-chat/stream/${streamIdentifier.toString()}" sse-swap="message" sse-close="completed" hx-swap="outerHTML">
    <footer><small>LLM</small></footer>
</article>