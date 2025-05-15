document.addEventListener('DOMContentLoaded', function() {
    // Initialize data tables
    const paymentsTable = document.querySelector('.payments-table');
    if (paymentsTable) {
        // This would be replaced with a proper table library like DataTables in production
        console.log('Payments table initialized');
    }

    // Logout button
    const logoutBtn = document.getElementById('logoutBtn');
    if (logoutBtn) {
        logoutBtn.addEventListener('click', function(e) {
            e.preventDefault();
            // Add logout logic here
            window.location.href = '/';
        });
    }

    // Generate Report button
    const generateReportBtn = document.querySelector('.card-actions .btn-primary');
    if (generateReportBtn) {
        generateReportBtn.addEventListener('click', function() {
            // Simulate report generation
            this.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Generating...';

            setTimeout(() => {
                this.innerHTML = '<i class="fas fa-file-pdf"></i> Report Generated';
                alert('Payment report generated successfully!');

                // Reset button after delay
                setTimeout(() => {
                    this.innerHTML = 'Generate Report';
                }, 2000);
            }, 1500);
        });
    }

    // Send payment reminder
    const remindButtons = document.querySelectorAll('.payments-table .btn-outline');
    remindButtons.forEach(button => {
        if (button.textContent.trim() === 'Remind') {
            button.addEventListener('click', function(e) {
                e.preventDefault();
                const row = this.closest('tr');
                const tenantName = row.querySelector('.tenant-avatar span').textContent;

                // Simulate sending reminder
                this.innerHTML = '<i class="fas fa-spinner fa-spin"></i>';

                setTimeout(() => {
                    this.innerHTML = '<i class="fas fa-check"></i> Sent';
                    alert(`Reminder sent to ${tenantName}`);

                    // Reset button after delay
                    setTimeout(() => {
                        this.innerHTML = 'Remind';
                    }, 2000);
                }, 1000);
            });
        }
    });

    // View maintenance request
    const viewRequestButtons = document.querySelectorAll('.request-actions .btn-outline');
    viewRequestButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            e.preventDefault();
            const requestItem = this.closest('.request-item');
            const requestTitle = requestItem.querySelector('h4').textContent;
            const tenantName = requestItem.querySelector('p').textContent;

            alert(`Viewing ${requestTitle} for ${tenantName}`);
        });
    });

    // New Tenant Modal
    const newTenantModal = document.getElementById('newTenantModal');
    const newTenantForm = document.getElementById('newTenantForm');

    // In a real app, this would be triggered by a button to add new tenant
    // For demo purposes, we'll show it after 2 seconds
    setTimeout(() => {
        if (newTenantModal) {
            newTenantModal.classList.add('active');
        }
    }, 2000);

    // Close modal
    const closeModal = document.querySelector('#newTenantModal .close-modal');
    if (closeModal) {
        closeModal.addEventListener('click', function() {
            newTenantModal.classList.remove('active');
        });
    }

    // Close modal when clicking outside
    window.addEventListener('click', function(e) {
        if (e.target === newTenantModal) {
            newTenantModal.classList.remove('active');
        }
    });

    // Form submission
    if (newTenantForm) {
        newTenantForm.addEventListener('submit', function(e) {
            e.preventDefault();

            const submitBtn = this.querySelector('button[type="submit"]');
            submitBtn.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Adding...';

            // Simulate form submission
            setTimeout(() => {
                submitBtn.innerHTML = '<i class="fas fa-check"></i> Tenant Added';
                alert('New tenant added successfully!');

                // Close modal after delay
                setTimeout(() => {
                    newTenantModal.classList.remove('active');
                    submitBtn.innerHTML = 'Add Tenant';
                    this.reset();
                }, 1500);
            }, 2000);
        });
    }

    // Cancel button in form
    const cancelBtn = document.querySelector('#newTenantForm .btn-outline');
    if (cancelBtn) {
        cancelBtn.addEventListener('click', function(e) {
            e.preventDefault();
            newTenantModal.classList.remove('active');
            newTenantForm.reset();
        });
    }
});