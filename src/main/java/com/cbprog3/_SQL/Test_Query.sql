select u.firstname as "Name", sum(e.expense_amt) as "Total Expenses"
from Users u
join User_Expense ue on u.user_id = ue.user_id
join Expense e on ue.expense_id = e.expense_id
group by 1
order by 2 desc