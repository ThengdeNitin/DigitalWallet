import { useLocation, useNavigate } from "react-router-dom"; 

function Main() {
  const location = useLocation();
  const navigate = useNavigate(); 
  const user = location.state?.user;
  const [transactions, setTransactions] = useState([]);
  const [balance, setBalance] = useState(user?.balance || 0);
  const [amount, setAmount] = useState("");

  useEffect(() => {
    if (user?.accountNumber) {
      fetch(`http://localhost:8080/api/transactions/${user.accountNumber}`)
        .then((response) => {
          if (!response.ok) {
            throw new Error("Failed to fetch transactions");
          }
          return response.json();
        })
        .then((data) => {
          console.log("Transactions:", data);
          setTransactions(data);
        })
        .catch((error) => console.error(error.message));
    }
  }, [user?.accountNumber]);

  if (!user) {
    return <h1 className="text-center text-red-500 font-bold">No user data found. Please login again.</h1>;
  }

  const handleDeposit = () => {
    if (!amount || amount <= 0) {
      alert("Enter a valid deposit amount");
      return;
    }

    fetch(`http://localhost:8080/api/transactions/deposit/${user.accountNumber}?amount=${amount}`, {
      method: "POST",
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Failed to deposit amount");
        }
        return response.json();
      })
      .then((data) => {
        console.log("Deposit successful:", data);
        setBalance(data.balance);
        setTransactions((prev) => [data, ...prev]);
      })
      .catch((error) => alert(error.message));
  };

  const handleWithdraw = () => {
    if (!amount || amount <= 0) {
      alert("Enter a valid withdrawal amount");
      return;
    }

    fetch(`http://localhost:8080/api/transactions/withdraw/${user.accountNumber}?amount=${amount}`, {
      method: "POST",
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Failed to withdraw amount");
        }
        return response.json();
      })
      .then((data) => {
        console.log("Withdrawal successful:", data);
        setBalance(data.balance);
        setTransactions((prev) => [data, ...prev]);
      })
      .catch((error) => alert(error.message));
  };

  const handleLogout = () => {
    navigate("/login"); 
  };

  return (
    <div className="flex flex-col items-center justify-center min-h-screen p-2 back-img-main">
      <div className="flex w-full max-w-7xl space-x-4">
        {/* User Information */}
        <div className="p-6 rounded-lg w-2/3 bg-amber-50 shadow-md">
          <h1 className="text-xl font-bold mb-4 flex items-center justify-center">User Details</h1>
          <div className="text-lg"><span className="font-bold">Name</span>: {user.accountHolderName}</div>
          <div className="text-lg"><span className="font-bold">Account Number</span>: {user.accountNumber}</div>
          <div className="text-lg"><span className="font-bold">Balance</span>: ₹{balance}</div>
          <button
            onClick={handleLogout}
            className="mt-4 bg-red-500 text-white px-4 py-2 rounded-lg hover:bg-red-600 transition font-bold"
          >
            Logout
          </button>
        </div>

      
        <div className="p-6 rounded-lg w-2/3 bg-amber-50 shadow-md">
          <h1 className="text-xl font-bold mb-4 text-center">Services</h1>
          <div className="flex space-x-4 text-center items-center justify-center">
            <input
              type="number"
              value={amount}
              onChange={(e) => setAmount(e.target.value)}
              className="border border-gray-300 rounded p-2 w-1/3"
              placeholder="Enter amount"
            />

            <button 
              onClick={handleDeposit}
              className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600"
            >
              <span className="font-bold">Deposit</span>
            </button>

            <button 
              onClick={handleWithdraw}
              className="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600"
            >
              <span className="font-bold">Withdraw</span>
            </button>
          </div>
        </div>
      </div>

      <div className="w-full max-w-7xl p-6 mt-6 bg-amber-50 rounded-lg shadow-md">
        <h1 className="text-xl font-bold mb-4 text-center">Transaction History</h1>
        {transactions.length > 0 ? (
          <table className="w-full border-collapse border border-gray-300">
            <thead>
              <tr className="bg-gray-200">
                <th className="border border-gray-300 px-4 py-2">Date</th>
                <th className="border border-gray-300 px-4 py-2">Deposit</th>
                <th className="border border-gray-300 px-4 py-2">Withdraw</th>
                <th className="border border-gray-300 px-4 py-2">Balance After</th>
              </tr>
            </thead>
            <tbody>
              {transactions.map((txn, index) => (
                <tr key={index} className="text-center">
                  <td className="border border-gray-300 px-4 py-2">{new Date(txn.dateTime).toLocaleString()}</td>
                  <td className="border border-gray-300 px-4 py-2">₹{txn.depositAmount}</td>
                  <td className="border border-gray-300 px-4 py-2">₹{txn.withdrawAmount}</td>
                  <td className="border border-gray-300 px-4 py-2">₹{txn.balance}</td>
                </tr>
              ))}
            </tbody>
          </table>
        ) : (
          <p className="text-center text-red-500">No transactions found.</p>
        )}
      </div>
    </div>
  );
}

export default Main;
