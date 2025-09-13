@param java.util.UUID streamIdentifier
<article class="llm-message" aria-busy="true" hx-swap-oob="beforeend:#message-box">
    <span sse-connect="/llm-chat/stream/${streamIdentifier.toString()}" sse-swap="message"></span>
    <footer><small>LLM</small></footer>
</article>