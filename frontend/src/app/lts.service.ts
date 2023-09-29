import { Injectable } from '@angular/core';
import { User } from './user';
import { Observable } from 'rxjs';
import { Leave } from './leave';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root',
})
export class LtsService {
  constructor(private http: HttpClient) {}

  public loginUser(user: User) {
    return this.http.post('http://localhost:8080/login', user);
  }
  public registerUser(user: User) {
    return this.http.post('http://localhost:8080/register', user);
  }
  public addLeave(leave: Leave) {
    return this.http.post('http://localhost:8080/applyleave', leave);
  }
  public getLeaveCount(email: string): Observable<number> {
    return this.http.get<number>(
      `http://localhost:8080/getLeaveCount?email=${email}`
    );
  }
  public getRemainingLeavesCount(email: string): Observable<number> {
    return this.http.get<number>(
      `http://localhost:8080/getRemainingleaves?email=${email}`
    );
  }
  public deleteLeave(leave_id: number) {
    return this.http.delete('http://localhost:8080/delete/' + leave_id);
  }
  public getLeavesByEmail(email: string): Observable<Leave[]> {
    return this.http.get<Leave[]>(
      `http://localhost:8080/getLeavesByEmail?email=${email}`
    );
  }
  public editLeave(leave: Leave, leave_id: number) {
    return this.http.put('http://localhost:8080/update/' + leave_id, leave);
  }

  public getLeaveById(leave_id: number): Observable<Leave> {
    return this.http.get<Leave>('http://localhost:8080/getleave/' + leave_id);
  }
  getAcceptedLeavesCount(status: string, email: string): Observable<number> {
    return this.http.get<number>(
      `http://localhost:8080/getAcceptedLeavesCount?status=${status}&email=${email}`
    );
  }
  getRejectedLeavesCount(status: string, email: string): Observable<number> {
    return this.http.get<number>(
      `http://localhost:8080/getRejectedLeavesCount?status=${status}&email=${email}`
    );
  }
  getPendingLeavesCount(status: string, email: string): Observable<number> {
    return this.http.get<number>(
      `http://localhost:8080/getPendingLeavesCount?status=${status}&email=${email}`
    );
  }

  public getLeavesByStatus(status: string): Observable<Leave[]> {
    return this.http.get<Leave[]>(
      `http://localhost:8080/getLeavesByStatus?status=${status}`
    );
  }
  acceptLeave(leaveId: number): Observable<any> {
    return this.http.put('http://localhost:8080/accept/' + leaveId, leaveId);
  }
  rejectLeave(leaveId: number): Observable<any> {
    return this.http.put('http://localhost:8080/reject/' + leaveId, leaveId);
  }

  updateComment(leaveId: number, comment: string): Observable<any> {
    return this.http.put(
      `http://localhost:8080/updateComment/${leaveId}`,
      comment
    );
  }

  incrementLeaveCount(incrementValue: number) {
    return this.http.put<string>(`http://localhost:8080/incrementLeaveCount?incrementValue=${incrementValue}`, incrementValue);
  }
  getLeaves() {
    return this.http.get('http://localhost:8080/getLeaves');
  }
}
