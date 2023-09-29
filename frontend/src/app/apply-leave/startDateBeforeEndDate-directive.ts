import { Directive, Input } from '@angular/core';
import { NG_VALIDATORS, Validator, AbstractControl, ValidationErrors } from '@angular/forms';

@Directive({
  selector: '[appStartDateBeforeEndDate][ngModel]',
  providers: [
    {
      provide: NG_VALIDATORS,
      useExisting: StartDateBeforeEndDateDirective,
      multi: true
    }
  ]
})
export class StartDateBeforeEndDateDirective implements Validator {
  @Input('appStartDateBeforeEndDate') endDate!: string; // Input to specify the end date field name

  validate(control: AbstractControl): ValidationErrors | null {
    const startDate = new Date(control.value);
    const endDateControl = control.root.get(this.endDate);

    if (!endDateControl) {
      return null; // If end date control is not found, return null
    }

    const endDate = new Date(endDateControl.value);

    if (startDate >= endDate) {
      return { startDateBeforeEndDate: true };
    }

    return null;
  }
}
