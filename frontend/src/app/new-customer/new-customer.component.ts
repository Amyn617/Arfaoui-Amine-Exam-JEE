import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CustomerService } from '../services/customer.service';
import { Customer } from '../model/customer.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-customer',
  standalone: false,
  templateUrl: './new-customer.component.html',
  styleUrl: './new-customer.component.css',
})
export class NewCustomerComponent implements OnInit {
  newCustomerFormGroup!: FormGroup;
  constructor(
    private fb: FormBuilder,
    private customerService: CustomerService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.newCustomerFormGroup = this.fb.group({
      name: this.fb.control(null, [
        Validators.required,
        Validators.minLength(4),
      ]),
      email: this.fb.control(null, [Validators.required, Validators.email]),
      phone: this.fb.control(null),
      address: this.fb.control(null),
      // Credit-specific fields
      creditScore: this.fb.control(null, [
        Validators.min(300),
        Validators.max(850),
      ]),
      annualIncome: this.fb.control(null, [Validators.min(0)]),
      employmentStatus: this.fb.control('EMPLOYED'),
      existingLoans: this.fb.control(0, [Validators.min(0)]),
    });
  }

  handleSaveCustomer() {
    let customer: Customer = this.newCustomerFormGroup.value;
    this.customerService.saveCustomer(customer).subscribe({
      next: (data) => {
        alert('Customer has been successfully saved!');
        //this.newCustomerFormGroup.reset();
        this.router.navigateByUrl('/customers');
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
}
