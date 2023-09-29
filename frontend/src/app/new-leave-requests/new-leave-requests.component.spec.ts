import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewLeaveRequestsComponent } from './new-leave-requests.component';

describe('NewLeaveRequestsComponent', () => {
  let component: NewLeaveRequestsComponent;
  let fixture: ComponentFixture<NewLeaveRequestsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NewLeaveRequestsComponent]
    });
    fixture = TestBed.createComponent(NewLeaveRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
