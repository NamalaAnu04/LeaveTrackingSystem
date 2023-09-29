import { Component,OnInit } from '@angular/core';
import { Leave } from '../leave';
import { ActivatedRoute, Router } from '@angular/router';
import { LtsService } from '../lts.service';

@Component({
  selector: 'app-edit-leave',
  templateUrl: './edit-leave.component.html',
  styleUrls: ['./edit-leave.component.css']
})
export class EditLeaveComponent implements OnInit {
 id!:number;
 leave: Leave=new Leave();
 constructor(private ltsService:LtsService,private route:ActivatedRoute,private router:Router){}
 ngOnInit(): void{
  this.id=this.route.snapshot.params['id'];
  this.ltsService.getLeaveById(this.id).subscribe(data=>{
    this.leave=data;
    console.log(data);
    
  },error=>console.log(error));
 }
 updateLeave() {
  this.ltsService.editLeave(this.leave,this.id ).subscribe(
    (response) => {
      console.log('Leave updated successfully:', response);
    },
    (error) => {
      console.error('Error updating leave:', error);
    }
  );
  this.router.navigate(['/track-leave']);
}
}
