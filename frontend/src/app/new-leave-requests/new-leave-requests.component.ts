import { Component } from '@angular/core';
import { LtsService } from '../lts.service';
import { Leave } from '../leave';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-leave-requests',
  templateUrl: './new-leave-requests.component.html',
  styleUrls: ['./new-leave-requests.component.css']
})
export class NewLeaveRequestsComponent {
  leaves: Leave[] = []
  show:boolean=false;
  constructor(private ltsservice: LtsService,private router:Router) {}
  leave_id!:number;
  comment: string = '';
  ngOnInit() {
    this.getleave();
  }
  getleave(){
    this.ltsservice.getLeavesByStatus("pending").subscribe(
      (data) => {
        this.leaves = data;
      },
      (error) => {
        console.error('Error fetching leaves:', error);
      }
    );
  }
  AcceptLeave(leaveId: number): void {
    this.ltsservice.acceptLeave(leaveId).subscribe(
      () => {
        this.getleave();
        
      }
    );
  }
  RejectLeave(leaveId: number): void {
    this.ltsservice.rejectLeave(leaveId).subscribe(
      () => {
        this.getleave();
        this.show=true;
        this.leave_id=leaveId;
      },
      (error) => {
        console.error('Error Rejecting leave:', error);
      }
    );
  }
  updateComment() {
    this.ltsservice.updateComment(this.leave_id, this.comment)
      .subscribe(
        response => {
          console.log('Comment updated:', response);
          this.show=false;
        },
        error => {
          console.error('Error updating comment:', error);
          // Handle error, e.g., show an error message
        }
      );
  }
  close(){
    this.show=false;
  }
  
}
