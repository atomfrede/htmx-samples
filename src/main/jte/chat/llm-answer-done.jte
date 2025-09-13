@param java.util.UUID streamIdentifier
@param String answer
<article class="llm-message"
         id="llm-answer-${streamIdentifier.toString()}"
         hx-swap-oob>
    ${answer}
    <footer><small>LLM</small></footer>
</article>