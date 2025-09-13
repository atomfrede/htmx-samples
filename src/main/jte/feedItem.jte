@param org.codeberg.atomfrede.htmx_samples.RealtimeService.FeedItem feedItem
<li id="news-feed" hx-swap-oob="afterbegin">
    <article>
        <header>${feedItem.author}</header>
        ${feedItem.message}
        <footer>${feedItem.timestamp.toString()}</footer>
    </article>
</li>