import { Component,OnInit} from '@angular/core';
import { LtsService } from '../lts.service';
import { NzProgressModule } from 'ng-zorro-antd/progress';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit{
  
  status!:string;
  AcceptedleaveCount!:number;
  RejectedleaveCount!:number;
  PendingleaveCount!:number;
  leaveCount!:number;
  RemainingleaveCount!:number;
  loginObj = JSON.parse(localStorage.getItem('loginUser') || '[]');
  constructor(private ltsService:LtsService){}
  ngOnInit(): void {
    this.getAcceptedLeavesCount();
    this.getRejectedLeavesCount();
    this.getPendingLeavesCount();
    this.loadLeaveCount();
    this.loadRemainingLeaveCount();
  }
  getAcceptedLeavesCount(): void {
    this.status="Accepted"
    this.ltsService.getAcceptedLeavesCount(this.status, this.loginObj.email)
      .subscribe(count => {
        this.AcceptedleaveCount = count;
      });
  }
  getRejectedLeavesCount(): void {
    this.status="Rejected"
    this.ltsService.getRejectedLeavesCount(this.status, this.loginObj.email)
      .subscribe(count => {
        this.RejectedleaveCount = count;
      });
  }
  getPendingLeavesCount(): void {
    this.status="pending"
    this.ltsService.getPendingLeavesCount(this.status, this.loginObj.email)
      .subscribe(count => {
        this.PendingleaveCount = count;
      });
  }
  loadLeaveCount() {
    this.ltsService.getLeaveCount(this.loginObj.email).subscribe(count=>{
        this.leaveCount =count;
        console.log(this.leaveCount)
  });
  }
  loadRemainingLeaveCount() {
    this.ltsService.getRemainingLeavesCount(this.loginObj.email).subscribe(count=>{
        this.RemainingleaveCount =count;
  },
  error => {
    console.error("Error fetching Accepted Leave Count:", error);
  });
  }

}
