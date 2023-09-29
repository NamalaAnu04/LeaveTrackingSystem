import { TestBed } from '@angular/core/testing';

import { LtsService } from './lts.service';

describe('LtsService', () => {
  let service: LtsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LtsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
