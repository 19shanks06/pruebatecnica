import { TestBed } from '@angular/core/testing';

import { UserSessionGuard } from './user-session.guard';

describe('UserSessionGuard', () => {
  let guard: UserSessionGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(UserSessionGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
