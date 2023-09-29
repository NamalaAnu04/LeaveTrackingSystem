import { Directive, Input } from '@angular/core';
import { NG_VALIDATORS, Validator, AbstractControl, ValidationErrors } from '@angular/forms';

@Directive({
  selector: '[appEndDateAfterStartDate][ngModel]',
  providers: [
    {
      provide: NG_VALIDATORS,
      useExisting: EndDateAfterStartDateDirective,
      multi: true
    }
  ]
})
export class EndDateAfterStartDateDirective implements Validator {
  @Input('appEndDateAfterStartDate') startDate!: string; 

  validate(control: AbstractControl): ValidationErrors | null {
    const endDate = new Date(control.value);
    const startDateControl = control.root.get(this.startDate);

    if (!startDateControl) {
      return null;
    }

    const startDate = new Date(startDateControl.value);
    startDate.setHours(0,0,0,0)
    if (endDate <= startDate) {
      return { endDateAfterStartDate: true };
    }

    return null;
  }
}
