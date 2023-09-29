import { Component } from '@angular/core';
import { LtsService } from '../lts.service';
import { Router } from '@angular/router';
import { NzModalService } from 'ng-zorro-antd/modal';
@Component({
  selector: 'app-leave-count-increment',
  templateUrl: './leave-count-increment.component.html',
  styleUrls: ['./leave-count-increment.component.css']
})
export class LeaveCountIncrementComponent {
  constructor(private ltsservice:LtsService,private router:Router,private modal: NzModalService,){}
  incrementValue!:number
  incrementLeave( ) {
    this.ltsservice.incrementLeaveCount(this.incrementValue)
    .subscribe(
      (response) => {
        this.modal.success({
          nzTitle: 'Leave Count Updated',
          nzOnOk: () => {
            this.router.navigate(['/overview']);
          }
        });
      },
      (error) => {
        // Handle the error here
        console.error('Error:', error);
      }
    );  
  }
}
