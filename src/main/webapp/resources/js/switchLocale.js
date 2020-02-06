function switchLocale(url) {
    var xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.open('GET', url, false);
    xmlHttpRequest.send();

    if (xmlHttpRequest.status != 200)
        alert(url + ', ' + xmlHttpRequest.status + ', ' + xmlHttpRequest.statusText);
    else
        location.reload(true);
}