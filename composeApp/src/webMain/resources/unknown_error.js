const unhandledError = (event, error) => {
        document.getElementById("warning-unknownError").style.display = "initial";
        document.getElementById("spinner").style.display = "none";
        document.getElementById("composeApp").style.display = "none";
        const metaThemeColor = document.querySelector("meta[name='theme-color']");
        if (metaThemeColor) {
            metaThemeColor.setAttribute("content", "#000000");
        }
}
addEventListener("error", (event) => unhandledError(event, event.error));
addEventListener("unhandledrejection", (event) => unhandledError(event, event.reason));