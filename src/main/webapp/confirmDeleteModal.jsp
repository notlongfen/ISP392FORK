
<!-- Modal -->
<div class="modal fade" id="confirmDeleteModal" tabindex="-1" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-danger text-white">
                <h5 class="modal-title" id="confirmDeleteModalLabel">Confirm Delete</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Are you sure you want to delete this item?
            </div>
            <div class="modal-footer">
                <form id="deleteForm" method="post">
                    <input type="hidden" name="id" id="modalID" value="">
                    <input type="hidden" name="parentid" id="modalParentID" value="">
                    <input type="hidden" name="deleteType" id="modalDeleteType" value="">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    document.querySelectorAll('.btn-danger[data-toggle="modal"]').forEach(btn => {
        btn.addEventListener('click', function () {
            const id = this.getAttribute('data-id');
            const parentid = this.getAttribute('data-parentid');
            const deleteType = this.getAttribute('data-delete-type');
            document.getElementById('modalID').value = id;
            document.getElementById('modalParentID').value = parentid;
            document.getElementById('modalDeleteType').value = deleteType;
            document.getElementById('deleteForm').action = deleteType === 'product' ? 'DeleteProductController' : 'DeleteProductDetailsController';
        });
    });
</script>
