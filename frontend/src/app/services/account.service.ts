import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class CreditService {
  private apiUrl = environment.apiUrl + '/credits';

  constructor(private http: HttpClient) {}

  getCreditById(creditId: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/${creditId}`);
  }

  getCreditPayments(
    creditId: string,
    page: number,
    size: number
  ): Observable<any> {
    return this.http.get(
      `${this.apiUrl}/${creditId}/payments?page=${page}&size=${size}`
    );
  }

  applyCreditForCustomer(
    customerId: number,
    creditAmount: number,
    duration: number,
    interestRate: number,
    description: string
  ): Observable<any> {
    return this.http.post(`${this.apiUrl}/apply`, {
      customerId,
      creditAmount,
      duration,
      interestRate,
      description,
    });
  }

  makePayment(
    creditId: string,
    amount: number,
    paymentDate: string
  ): Observable<any> {
    return this.http.post(`${this.apiUrl}/${creditId}/payments`, {
      amount,
      paymentDate,
    });
  }

  getCreditsForCustomer(customerId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/customer/${customerId}`);
  }

  calculateCreditSchedule(
    amount: number,
    duration: number,
    interestRate: number
  ): Observable<any> {
    return this.http.post(`${this.apiUrl}/calculate`, {
      amount,
      duration,
      interestRate,
    });
  }
}
