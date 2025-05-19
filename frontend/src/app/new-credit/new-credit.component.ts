import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CustomerService } from '../services/customer.service';
import { CreditService } from '../services/account.service';
import { Router } from '@angular/router';
import { Customer } from '../model/customer.model';

@Component({
  selector: 'app-new-credit',
  standalone: false,
  templateUrl: './new-credit.component.html',
  styleUrls: ['./new-credit.component.css'],
})
export class NewCreditComponent implements OnInit {
  newCreditFormGroup!: FormGroup;
  customers: Customer[] = [];
  isLoading: boolean = false;
  errorMessage: string = '';
  paymentSchedule: any[] = [];
  showSchedule: boolean = false;

  constructor(
    private fb: FormBuilder,
    private customerService: CustomerService,
    private creditService: CreditService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.newCreditFormGroup = this.fb.group({
      customerId: [null, [Validators.required]],
      amount: [null, [Validators.required, Validators.min(1000)]],
      duration: [
        12,
        [Validators.required, Validators.min(3), Validators.max(360)],
      ],
      interestRate: [5.5, [Validators.required, Validators.min(0.1)]],
      description: [''],
    });

    this.loadCustomers();
  }

  loadCustomers() {
    this.isLoading = true;
    this.customerService.getCustomer().subscribe({
      next: (data) => {
        this.customers = data;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Error loading customers:', err);
        this.errorMessage = 'Failed to load customers. Please try again.';
        this.isLoading = false;
      },
    });
  }

  calculatePaymentSchedule() {
    if (this.newCreditFormGroup.invalid) {
      this.validateAllFormFields();
      return;
    }

    this.isLoading = true;
    const amount = this.newCreditFormGroup.value.amount;
    const duration = this.newCreditFormGroup.value.duration;
    const interestRate = this.newCreditFormGroup.value.interestRate;

    this.creditService
      .calculateCreditSchedule(amount, duration, interestRate)
      .subscribe({
        next: (data) => {
          this.paymentSchedule = data;
          this.showSchedule = true;
          this.isLoading = false;
        },
        error: (err) => {
          console.error('Error calculating payment schedule:', err);
          this.errorMessage =
            'Failed to calculate payment schedule. Please try again.';
          this.isLoading = false;
        },
      });
  }

  applyForCredit() {
    if (this.newCreditFormGroup.invalid) {
      this.validateAllFormFields();
      return;
    }

    this.isLoading = true;
    const formValues = this.newCreditFormGroup.value;

    this.creditService
      .applyCreditForCustomer(
        formValues.customerId,
        formValues.amount,
        formValues.duration,
        formValues.interestRate,
        formValues.description
      )
      .subscribe({
        next: (data) => {
          this.isLoading = false;
          alert('Credit application submitted successfully!');
          this.router.navigateByUrl('/credits');
        },
        error: (err) => {
          this.isLoading = false;
          this.errorMessage =
            'Failed to submit credit application. Please check the details and try again.';
          console.error(err);
        },
      });
  }

  private validateAllFormFields() {
    Object.keys(this.newCreditFormGroup.controls).forEach((field) => {
      const control = this.newCreditFormGroup.get(field);
      control?.markAsTouched({ onlySelf: true });
    });
  }

  cancelApply() {
    this.router.navigateByUrl('/credits');
  }
}
