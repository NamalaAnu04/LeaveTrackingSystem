import { Component } from '@angular/core';
import { Leave } from '../leave';
import { NzModalService } from 'ng-zorro-antd/modal';
import { LtsService } from '../lts.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-apply-leave',
  templateUrl: './apply-leave.component.html',
  styleUrls: ['./apply-leave.component.css']
})
export class ApplyLeaveComponent {
  loginObj = JSON.parse(localStorage.getItem('loginUser') || '[]');
  errormsg!:String;
  constructor(
    private ltsService: LtsService,
    private modal: NzModalService,
    private router:Router
  ) {}

  ngOnInit() {
  }

  ApplyLeave(leave: Leave) {
    const email = this.loginObj.email;
    
    this.ltsService.getRemainingLeavesCount(email).subscribe(
      (remainingLeaves: number) => {
        if (remainingLeaves <= 0) {
          this.modal.error({
            nzTitle: 'Leave Application Failed',
            nzContent: 'You have no remaining leaves.',
            nzOnOk: () => {
              this.router.navigate(['/dashboard']);
            }
          });
        } else {
          leave.email = email;
          this.ltsService.addLeave(leave).subscribe(
            (response) => {
              this.modal.success({
                nzTitle: 'Leave Applied Successfully',
                nzContent: 'We will let you know the Status',
                nzOnOk: () => {
                  this.router.navigate(['/track-leave']);
                }
              });
            }
          );
        }
      },
      (error) => {
        // Handle any errors from the API request
        console.error('Error fetching remaining leaves:', error);
      }
    );
  }
  
}
