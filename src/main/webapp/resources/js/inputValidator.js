window.onload = function () {
    function setInputFilter(textbox, inputFilter) {
        ["input", "keydown", "keyup", "mousedown", "mouseup", "select", "contextmenu", "drop"].forEach(function (event) {
            textbox.addEventListener(event, function () {
                if (inputFilter(this.value)) {
                    this.oldValue = this.value;
                    this.oldSelectionStart = this.selectionStart;
                    this.oldSelectionEnd = this.selectionEnd;
                } else if (this.hasOwnProperty("oldValue")) {
                    this.value = this.oldValue;
                    this.setSelectionRange(this.oldSelectionStart, this.oldSelectionEnd);
                } else {
                    this.value = "";
                }
            });
        });
    }

    setInputFilter(document.getElementById("ccnum"), function (value) {
        return /^(\d{1,4}[-]?\d{0,4}[-]?\d{0,4}[-]?\d{0,4})$/.test(value);
    });

    setInputFilter(document.getElementById("cvv"), function (value) {
        return /^\d+/.test(value);
    });

    setInputFilter(document.getElementById("cname"), function (value) {
        return /^([A-Z]+[ ]?[A-Z]*)$/.test(value);
    });

    setInputFilter(document.getElementById("expdate"), function (value) {
        return /^\d{1,2}[\/]?[\d]?[\d]?$/.test(value);
    });

    setInputFilter(document.getElementById("input-quantity"), function (value) {
        return /^\d+$/.test(value);
    });

    // setInputFilter(document.getElementById("price"), function (value) {
    //     return /^\d{1,3}[.,]?[\d]{0,2}?$/.test(value);
    // });
    //
    // setInputFilter(document.getElementById("amount-pack"), function (value) {
    //     return /^\d{1,4}$/.test(value);
    // });

}
