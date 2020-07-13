### Thrift Tutorial

---

- [Thrift Tutorial](https://thrift-tutorial.readthedocs.io/en/latest/intro.html)

#### Installation

---

- Thrift를 적용할 언어: Java, 따라서 JDK를 설치해야 한다
- Java와 함께 Thrift를 적용하기 위해서는 Apache Ant를 설치해야 한다

  ```bash
  sudo apt-get install ant
  For the system Java wrappers to find this JDK, symlink it with
  sudo ln -sfn /usr/local/opt/openjdk/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk.jdk

  openjdk is keg-only, which means it was not symlinked into /usr/local,
  because it shadows the macOS `java` wrapper.

  If you need to have openjdk first in your PATH run:
  echo 'export PATH="/usr/local/opt/openjdk/bin:$PATH"' >> ~/.zshrc

  For compilers to find openjdk you may need to set:
  export CPPFLAGS="-I/usr/local/opt/openjdk/include" ### Need to be configured!
  ```

- Thrift 및 Tools & Libraries 설치 [Link](http://thrift.apache.org/docs/install/os_x)
  - [boost.org](https://www.boost.org)에서 boost 다운로드
    ```bash
    # tar -xvf 압축 해제 후
    ./bootstrap.sh
    sudo ./b2 threading=multi address-model=64 variant=release stage install
    ```
  - [libevent.org](https://libevent.org)에서 libevent 다운로드
    ```bash
    # tar -xvf 압축 해제 후
    ./configure --prefix=/usr/local --disable-openssl
    ```
  - [http://thrift.apache.org/download]에서 Thrift 다운로드 후
    ```bash
    # Bison
    brew install bison # Version Check에서 통과 못함. link 해제 후 업데이트한 bison 재링크
    brew unlink bison
    echo 'export PATH="/usr/local/opt/bison/bin:$PATH"' >> ~/.zshrc
    export LDFLAGS="-L/usr/local/opt/bison/lib"
    source ~/.zshrc
    bison -V
    #openssl
    echo 'export PATH="/usr/local/opt/openssl/bin:$PATH"' >> ~/.zshrc
    source ~/.zshrc
    ```
    - 와 같은 과정이 필요..
    - Homebrew로 설치
      ```bash
      brew install thrift
      ```
