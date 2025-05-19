import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CreditService } from '../services/account.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-credits',
  standalone: false,
  templateUrl: './accounts.component.html',
  styleUrl: './accounts.component.css',
})
export class CreditsComponent implements OnInit {
  // Stats for dashboard cards
  totalCredits: number = 0;
  activeCredits: number = 0;
  paidCredits: number = 0;
  defaultedCredits: number = 0;

  searchFormGroup: FormGroup | undefined;
  credits: any[] = [];
  isLoading: boolean = true;
  errorMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private creditService: CreditService,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Initialize with dummy data for demonstration
    this.totalCredits = 127;
    this.activeCredits = 98;
    this.paidCredits = 24;
    this.defaultedCredits = 5;

    this.searchFormGroup = this.fb.group({
      keyword: this.fb.control(''),
    });

    // In a real implementation, you would fetch actual credits from the service
    this.generateDummyCredits();
  }

  generateDummyCredits() {
    this.isLoading = true;

    // Simulate API delay
    setTimeout(() => {
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
          customerName: 'John Doe',
          customerId: 1,
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
          customerName: 'Jane Smith',
          customerId: 2,
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
          customerName: 'Michael Johnson',
          customerId: 3,
        },
      ];
      this.isLoading = false;
    }, 1000);
  }

  handleSearch() {
    // Implement search functionality
    const keyword = this.searchFormGroup?.value.keyword?.trim().toLowerCase();
    if (!keyword) {
      this.generateDummyCredits();
      return;
    }

    // Filter existing credits based on keyword (for demonstration)
    this.isLoading = true;
    setTimeout(() => {
      this.credits = this.credits.filter(
        (credit) =>
          credit.customerName.toLowerCase().includes(keyword) ||
          credit.id.toLowerCase().includes(keyword)
      );
      this.isLoading = false;
    }, 500);
  }

  viewCustomerCredits(customerId: number) {
    this.router.navigate(['/customer-credits', customerId]);
  }

  getCreditBadgeClass(status: string) {
    switch (status) {
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
