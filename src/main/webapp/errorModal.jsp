<div class="modal fade" id="errorModal" tabindex="-1" role="dialog" aria-labelledby="errorModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header bg-danger text-white">
                <h5 class="modal-title" id="errorModalLabel">Error</h5>
                <button type="button" class="close text-white" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <ul class="list-group list-group-flush">
                    <%
                        ProductError productError = (ProductError) request.getAttribute("PRODUCT_ERROR");
                        String Error = (String) request.getAttribute("ERROR_MESSAGE");
                        if (productError != null && !productError.getProductNameError().isEmpty()) {
                    %>
                    <li class="list-group-item list-group-item-danger"><%= productError.getProductNameError() %></li>

                    <%
                        }
                        if (Error != null && !Error.isEmpty()){
                    %>
                    <li class="list-group-item list-group-item-danger"><%= Error %></li>
                        <% 
                                  }%>
                </ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>