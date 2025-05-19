import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Customer } from '../model/customer.model';
import { CustomerService } from '../services/customer.service';
import { CreditService } from '../services/account.service';

interface Credit {
  id: string;
  amount: number;
  remainingAmount: number;
  startDate: Date;
  endDate: Date;
  duration: number;
  interestRate: number;
  monthlyPayment: number;
  status: string;
  customerId: number;
}

@Component({
  selector: 'app-customers-credits',
  standalone: false,
  templateUrl: './customers-accounts.component.html',
  styleUrl: './customers-accounts.component.css',
})
export class CustomerCreditsComponent implements OnInit {
  customerId: string = '';
  customer: Customer | null = null;
  credits: Credit[] = [];

  // Credit statistics
  creditsCount: number = 0;
  activeCreditsCount: number = 0;
  paidCreditsCount: number = 0;
  totalCreditAmount: number = 0;
  lastPaymentAmount: number = 0;
  lastPaymentDate: Date = new Date();
  creditScore: number = 0;

  constructor(
    private route: ActivatedRoute,
    private customerService: CustomerService,
    private creditService: CreditService
  ) {}

  ngOnInit(): void {
    this.customerId = this.route.snapshot.params['id'];

    // Load customer data if ID is available
    if (this.customerId) {
      this.loadCustomer();
      this.loadCredits();
    }
  }

  loadCustomer(): void {
    // Convert string customerId to number before passing to the service
    const customerIdNumber = parseInt(this.customerId, 10);

    this.customerService.getCustomerById(customerIdNumber).subscribe({
      next: (data) => {
        this.customer = data;
        this.creditScore = data.creditScore || 700; // Default if not provided
      },
      error: (err) => {
        console.error('Error loading customer:', err);
        // Fallback to a dummy customer for demonstration
        this.customer = {
          id: customerIdNumber,
          name: 'Sample Customer',
          email: 'sample@example.com',
          status: 'ACTIVE',
          creditScore: 700,
        };
      },
    });
  }

  loadCredits(): void {
    const customerIdNumber = parseInt(this.customerId, 10);

    this.creditService.getCreditsForCustomer(customerIdNumber).subscribe({
      next: (data) => {
        this.credits = data;
        // Calculate statistics
        this.calculateCreditStats();
      },
      error: (err) => {
        console.error('Error loading credits:', err);
        // For demonstration, create dummy credits
        this.generateDummyCredits();
        this.calculateCreditStats();
      },
    });
  }

  generateDummyCredits(): void {
    this.credits = [
      {
        id: 'CRD-001',
        amount: 15000,
        remainingAmount: 10500,
        startDate: new Date(2023, 2, 15),
        endDate: new Date(2025, 2, 15),
        duration: 24,
        interestRate: 4.5,
        monthlyPayment: 650.25,
        status: 'ACTIVE',
        customerId: parseInt(this.customerId, 10),
      },
      {
        id: 'CRD-002',
        amount: 5000,
        remainingAmount: 0,
        startDate: new Date(2022, 1, 10),
        endDate: new Date(2023, 1, 10),
        duration: 12,
        interestRate: 3.75,
        monthlyPayment: 428.5,
        status: 'PAID',
        customerId: parseInt(this.customerId, 10),
      },
      {
        id: 'CRD-003',
        amount: 25000,
        remainingAmount: 23750,
        startDate: new Date(2023, 5, 20),
        endDate: new Date(2028, 5, 20),
        duration: 60,
        interestRate: 5.25,
        monthlyPayment: 475.75,
        status: 'ACTIVE',
        customerId: parseInt(this.customerId, 10),
      },
    ];
  }

  calculateCreditStats(): void {
    this.creditsCount = this.credits.length;
    this.activeCreditsCount = this.credits.filter(
      (credit) => credit.status === 'ACTIVE'
    ).length;
    this.paidCreditsCount = this.credits.filter(
      (credit) => credit.status === 'PAID'
    ).length;
    this.totalCreditAmount = this.credits.reduce(
      (sum, credit) => sum + credit.amount,
      0
    );

    // Set last payment information (dummy data for demonstration)
    this.lastPaymentAmount = 475.75;
    this.lastPaymentDate = new Date(Date.now() - 7 * 24 * 60 * 60 * 1000); // 7 days ago
  }

  getInitials(name: string): string {
    if (!name) return '?';

    const nameParts = name.split(' ');
    if (nameParts.length === 1) {
      return name.charAt(0).toUpperCase();
    }

    return (
      nameParts[0].charAt(0).toUpperCase() +
      nameParts[nameParts.length - 1].charAt(0).toUpperCase()
    );
  }

  getStatusBadgeClass(customer: Customer | null) {
    if (!customer) return 'badge-active';

    switch (customer.status) {
      case 'ACTIVE':
        return 'badge-active';
      case 'INACTIVE':
        return 'badge-inactive';
      case 'PENDING':
        return 'badge-pending';
      default:
        return 'badge-active'; // Default to active
    }
  }

  getCreditBadgeClass(credit: Credit) {
    switch (credit.status) {
      case 'ACTIVE':
        return 'badge-active';
      case 'PAID':
        return 'badge-inactive';
      case 'DELAYED':
        return 'badge-pending';
      case 'DEFAULT':
        return 'badge-inactive';
      default:
        return 'badge-active'; // Default to active
    }
  }
}
