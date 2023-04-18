import { expect } from "expect";
import {
	getFormattedDate,
  getDateRange,
  createMapWithLast28Days,
  populateMapWithFailures,
} from "../../src/views/AdminDashboard/components/LoginFailureList/aggregateFailures";

const getExpectedDateMap = (dateString) => {
  const expected = new Map();
  let { lastDate, startDate } = getDateRange(dateString);

  while (startDate.toDateString() !== lastDate.toDateString()) {
    startDate.setDate(startDate.getDate() + 1);
    let day = startDate.getDate();
    let month = startDate.getMonth() + 1;
    const year = startDate.getFullYear();

    if (day < 10) {
      day = "0" + day;
    }

    if (month < 10) {
      month = `0${month}`;
    }

    const formattedDate = `${year}-${month}-${day}`;
    expected.set(formattedDate, 0);
  }
  return expected;
};

test("createMapWithLast28DaysTest", () => {
  const dateString = "2023-01-08";
  const expected = getExpectedDateMap(dateString);
  const actual = createMapWithLast28Days(dateString);
  expect(actual).toEqual(expected);
});

test("populateMapWithFailuresTest", () => {
  const dateMap = new Map();
  const failureList = ["2022-12-15", "2022-12-15", "2022-12-16"];
  dateMap.set("2022-12-15", 0);
  dateMap.set("2022-12-16", 0);
  const actual = populateMapWithFailures(dateMap, failureList);
  const expected = new Map();
  expected.set("2022-12-15", 2);
  expected.set("2022-12-16", 1);
  expect(actual).toEqual(expected);
});

test("getDateRange", () => {
  let startDate = new Date("2022-12-11")
  let lastDate = new Date("2023-01-08")

  startDate.setDate(startDate.getUTCDate())
  lastDate.setDate(lastDate.getUTCDate())
  
  const dateString = "2023-01-08";
  const expected = {
    startDate: startDate,
    lastDate: lastDate,
  };
  
  const actual = getDateRange(dateString);
  expect(actual.startDate.toDateString()).toEqual(expected.startDate.toDateString());
  expect(actual.lastDate.toDateString()).toEqual(expected.lastDate.toDateString());
});

test("getFormattedDate", () => {
	const date = new Date("2023-01-16");
	date.setDate(date.getUTCDate());

	const expected = "2023-01-16";
	const actual = getFormattedDate(date);
	
	expect(actual).toEqual(expected);
});
