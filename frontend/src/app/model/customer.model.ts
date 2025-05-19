export interface Customer {
  id: number;
  name: string;
  email: string;
  phone?: string;
  address?: string;
  createdAt?: Date;
  status?: 'ACTIVE' | 'INACTIVE' | 'PENDING';
  // Credit-specific fields
  creditScore?: number;
  annualIncome?: number;
  employmentStatus?: 'EMPLOYED' | 'SELF_EMPLOYED' | 'UNEMPLOYED' | 'RETIRED';
  existingLoans?: number;
}
