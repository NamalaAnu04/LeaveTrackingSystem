import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employee-header',
  templateUrl: './employee-header.component.html',
  styleUrls: ['./employee-header.component.css']
})
export class EmployeeHeaderComponent {
  showButtons: boolean = false;
  showLogout:boolean=false;
  constructor(private router:Router){}
  toggleButtons() {
    this.showButtons = !this.showButtons;
  }

  isButtonWhiteText: boolean = false;
  toggleButtonTextColor() {
    this.isButtonWhiteText = !this.isButtonWhiteText;
  }

  isApplyLeaveBlack: boolean = false;
  isTrackLeaveBlack: boolean = false;
  isleaveHistoryBlack:boolean=false;

  toggleApplyLeaveColor() {
    this.isApplyLeaveBlack = !this.isApplyLeaveBlack;
    this.isTrackLeaveBlack = false; 
    this.isleaveHistoryBlack=false;
  }

  toggleTrackLeaveColor() {
    this.isTrackLeaveBlack = !this.isTrackLeaveBlack;
    this.isApplyLeaveBlack = false;
    this.isleaveHistoryBlack=false;
  }
  toggleleaveHistoryColor() {
    this.isleaveHistoryBlack = !this.isleaveHistoryBlack;
    this.isApplyLeaveBlack = false;
    this.isTrackLeaveBlack=false;
  }

  showLogOut(){
    this.showLogout=!this.showLogout;
  }

  loginObj=JSON.parse(localStorage.getItem('loginUser')||'[]');

  logout(){
    localStorage.removeItem('loginUser');
    this.router.navigate(["/login"]);
  }

  applyLeave(){
    this.router.navigate(["/apply-leave"]);
  }

  trackLeave(){
    this.router.navigate(["/track-leave"]);
  }

  leaveHistory(){
    this.router.navigate(["/dashboard"]);
  }
}
