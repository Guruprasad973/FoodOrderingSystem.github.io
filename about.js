<script>
    function redirectToPage2() 
    {
        // Get data from an input field or other source
        var dataToSend = "Hello from Page 1";
        
        // Redirect to Page 2 with data as a URL parameter
        window.location.href = "page2.html?data=" + encodeURIComponent(dataToSend);
    }
</script>