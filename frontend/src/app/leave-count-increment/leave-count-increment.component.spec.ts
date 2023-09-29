import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LeaveCountIncrementComponent } from './leave-count-increment.component';

describe('LeaveCountIncrementComponent', () => {
  let component: LeaveCountIncrementComponent;
  let fixture: ComponentFixture<LeaveCountIncrementComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LeaveCountIncrementComponent]
    });
    fixture = TestBed.createComponent(LeaveCountIncrementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
